package com.upsoft.environmentmonitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.environmentmonitoring.domain.po.StationTypeBean;
import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.constant.HourDayType;
import com.upsoft.environmentmonitoring.constant.StationType;
import com.upsoft.environmentmonitoring.domain.po.AqiReportHour;
import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.Factor;
import com.upsoft.environmentmonitoring.domain.vo.RatioVo;
import com.upsoft.environmentmonitoring.domain.vo.StationAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.StationRatioVO;
import com.upsoft.environmentmonitoring.domain.vo.StationReportVO;
import com.upsoft.environmentmonitoring.domain.vo.StationVO;
import com.upsoft.environmentmonitoring.domain.vo.WarpWeft;
import com.upsoft.environmentmonitoring.dao.AqiReportHourMapper;
import com.upsoft.environmentmonitoring.dao.StationAqiMapper;
import com.upsoft.environmentmonitoring.service.StationAqiService;
import com.upsoft.environmentmonitoring.utils.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StationAqiServiceImpl
 * @Description 监测站点AQI和6项因子的小时监测数据和天监测数据 服务实现类
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@Service("stationAqiServiceImpl")
public class StationAqiServiceImpl implements StationAqiService {

   /**
	 * @Description: 注入StationAqiMapper
	 */
	@Autowired
	private StationAqiMapper stationAqiMapper;

	/**
	 * @Description: 注入AqiReportHourMapper
	 */
	@Autowired
	private AqiReportHourMapper  aqiReportHourMapper;
	
	/**
	 * @Description: 注入stationTypeBean
	 */
	@Autowired
	private StationTypeBean stationTypeBean;
	

    /**
	 * @Author Wei Wei
	 * @Description 查询单个StationAqi
	 * @Date 2019-11-01
	 * @Params [StationAqi]
	 * @return StationAqi
	 **/
    @Override
    public StationAqi getStationAqi(StationAqi stationAqi) {
        return stationAqiMapper.getStationAqi(stationAqi);
    }

    /**
     * @Author weiwei
     * @Description 获取四川省监测站点AQI数据最后更新时间
     * @Date 9:15 2019/11/6
     * @Params [stationAqiType]
     * @return java.time.LocalDateTime
     **/
	@Override
	public LocalDateTime getLatestTimeForStationAqi(String stationAqiType) {
		return stationAqiMapper.getLatestTimeForStationAqi(stationAqiType);
	}

	/**
	 * @Author Wang wanqian
	 * @Description 获取监测地图站点分布情况
	 * @Date 2019/11/15
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	@Override
	public List<StationAqiVO> getStationAqiByOperation(StationAqi stationAqi) {
		List<StationAqiVO> list=null;
		if(isNUllCityCode(stationAqi)){
			return list;
		}else{
			getNewestTime(stationAqi);
			List<StationAqi> listStation=stationAqiMapper.getStationAqiByOperation(stationAqi);
			if(null!=listStation&&listStation.size()>0){
				list=new ArrayList<StationAqiVO>();
				list=IntegrationStation(list,listStation);
			}
			// 添加微站点的数据
			return addAqiReportHour(list,stationAqi);
		}
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 设置standby2 时间点
	 * @Date 16:13 2019/12/02
	 * @Params stationAqi
	 * @return 
	 **/
	private void getNewestTime(StationAqi stationAqi) {
		if(null==stationAqi.getStandby2()||stationAqi.getStandby2().equals("")){
			SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String sTime=stationAqiMapper.getNewestTime();
			String aTime=aqiReportHourMapper.getNewestTime();
			if(null!=sTime&&null!=aTime){
				try {
					if(format.parse(sTime).before(format.parse(aTime))){
						stationAqi.setStandby2(aTime);
					}else{
						stationAqi.setStandby2(sTime);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Author Wang wanqian
	 * @Description 判断stationName是否包含在配置文件中，再设置返回类型
	 * @Date 10:13 2019/11/21
	 * @Params []
	 * @return boolean
	 **/
	private StationAqi containsStationName(StationAqi sa){
		if(stationTypeBean.getSC().contains(sa.getStationName())){
			sa.setStationType(StationType.STATE_CONTROL.getStationType());
		}else if(stationTypeBean.getPC().contains(sa.getStationName())){
			sa.setStationType(StationType.PROVINCIAL_CONTROL.getStationType());
		}else if(stationTypeBean.getCC().contains(sa.getStationName())){
			sa.setStationType(StationType.CITY_CONTROL.getStationType());
		}else if(stationTypeBean.getMS().contains(sa.getStationName())){
			sa.setStationType(StationType.MICRO_STATION.getStationType());
		}
		return sa;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 判断stationName是否包含在配置文件中，再设置返回类型
	 * @Date 10:13 2019/11/21
	 * @Params []
	 * @return boolean
	 **/
	private AqiReportHour containsStationName(AqiReportHour ar){
		if(stationTypeBean.getSC().contains(ar.getPointName())){
			ar.setStandby4(StationType.STATE_CONTROL.getStationType());
		}else if(stationTypeBean.getPC().contains(ar.getPointName())){
			ar.setStandby4(StationType.PROVINCIAL_CONTROL.getStationType());
		}else if(stationTypeBean.getCC().contains(ar.getPointName())){
			ar.setStandby4(StationType.CITY_CONTROL.getStationType());
		}else if(stationTypeBean.getMS().contains(ar.getPointName())){
			ar.setStandby4(StationType.MICRO_STATION.getStationType());
		}
		return ar;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 整合返回实体的数据
	 * @Date 10:13 2019/11/19
	 * @Params [list，listStation]
	 * @return 
	 **/
	private List<StationAqiVO> IntegrationStation(List<StationAqiVO> list,List<StationAqi> listStation) {
		for(StationAqi sa:listStation){
			if(null!=sa.getStationName()&&!sa.getStationName().equals("")){
				sa=containsStationName(sa);
			}
			list.add(addStationAqiVO(sa));
		}
		return list;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 处理数据
	 * @Date 10:13 2019/11/19
	 * @Params [stationAqi]
	 * @return StationAqiVO
	 **/
	private StationAqiVO addStationAqiVO(StationAqi stationAqi) {
		StationAqiVO savo=new StationAqiVO();
		Factor factor=new Factor();
		WarpWeft wrapweft=new WarpWeft();
		factor.setAqi(StringUtil.valueToString(stationAqi.getAqi()))
		.setCo(StringUtil.valueToString(stationAqi.getCo()))
		.setNo2(StringUtil.valueToString(stationAqi.getNo2()))
		.setO3(StringUtil.valueToString(stationAqi.getO3()))
		.setSo2(StringUtil.valueToString(stationAqi.getSo2()))
		.setPm25(StringUtil.valueToString(stationAqi.getPm25()))
		.setPm10(StringUtil.valueToString(stationAqi.getPm10()));
		wrapweft.setLng(stationAqi.getStationLongitude())
		.setLat(stationAqi.getStationLatitude());
		savo.setId(stationAqi.getStationAqiId())
		.setAirVal(factor)
		.setPublishDateTime(stationAqi.getPublishDateTime())
		.setGeo(wrapweft)
		.setMonitorType(stationAqi.getStationAqiType())
		.setStationCode(stationAqi.getStationCode())
		.setStationName(stationAqi.getStationName())
		.setStationType(stationAqi.getStationType());
		return savo;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 判断城市code是否为空,是则返回true,否则返回false
	 * @Date 11:13 2019/11/15
	 * @Params [cityAqi]
	 * @return boolean
	 **/
	private boolean isNUllCityCode(StationAqi stationAqi){
		if(null==stationAqi.getCityCode()||stationAqi.getCityCode().equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 判断站点code是否为空,是则返回true,否则返回false
	 * @Date 11:13 2019/11/15
	 * @Params [cityAqi]
	 * @return boolean
	 **/
	private boolean isNUllStationCode(StationAqi stationAqi){
		if(null==stationAqi.getStationCode()||stationAqi.getStationCode().equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 监测地图-单个站点不同因子不同时间段折线图
	 * @Date 2019/11/15
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	@Override
	public List<StationAqiVO> getSingleStationAqiThrend(StationAqi stationAqi) {
		List<StationAqiVO> list=null;
		if(isNUllCityCode(stationAqi)||isNUllStationCode(stationAqi)){
			return null;
		}else{
			List<StationAqi> listStation=stationAqiMapper.getSingleStationAqiThrend(stationAqi);
			if(null!=listStation&&listStation.size()>0){
				list=new ArrayList<StationAqiVO>();
				list=IntegrationStation(list,listStation);
			}
			// 添加微站点的数据
			return addSingleAqiThrend(list,stationAqi);
		}
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 监测地图-添加微站点的分布数据
	 * @Date 2019/11/18
	 * @Params [list,stationAqi]
	 * @return List<StationAqi>
	 **/
	public List<StationAqiVO> addAqiReportHour(List<StationAqiVO> list,StationAqi stationAqi){
		AqiReportHour aqiReportHour=new AqiReportHour();
		aqiReportHour.setXzsqCode(stationAqi.getCityCode())
		.setStandby1(stationAqi.getStandby1()).setStandby2(stationAqi.getStandby2());
		List<AqiReportHour> listAqi=aqiReportHourMapper.getAqiReportByOperation(aqiReportHour);
		if(null!=listAqi&&listAqi.size()>0){
			return AqiReportHour(list,listAqi);
		}
		return list;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 监测地图-添加单个微站点的不同因子不同时间段折线图
	 * @Date 2019/11/18
	 * @Params [list,stationAqi]
	 * @return List<StationAqi>
	 **/
	public List<StationAqiVO> addSingleAqiThrend(List<StationAqiVO> list,StationAqi stationAqi){
		AqiReportHour aqiReportHour=new AqiReportHour();
		aqiReportHour.setXzsqCode(stationAqi.getCityCode())
		.setStandby1(stationAqi.getStationCode())
		.setStandby2(stationAqi.getStandby2())
		.setStandby3(stationAqi.getStandby3());
		List<AqiReportHour> listAqi=aqiReportHourMapper.getSingleAqiReportThrend(aqiReportHour);
		return AqiReportHour(list,listAqi);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 监测地图-添加单个微站点的不同因子不同时间段折线图
	 * @Date 2019/11/18
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	public List<StationAqiVO> AqiReportHour(List<StationAqiVO> list,List<AqiReportHour> listAqi){
		for(AqiReportHour ar:listAqi){
			if(null!=ar.getPointName()&&!ar.getPointName().equals("")){
				ar=containsStationName(ar);
			}
			list.add(addStationAqiVO(ar));
		}
		return list;
	}

	/**
	 * @param stationAqi 
	 * @Author Wang wanqian
	 * @Description 处理数据
	 * @Date 10:13 2019/11/19
	 * @Params [savo，factor，wrapweft]
	 * @return 
	 **/
	private StationAqiVO addStationAqiVO(AqiReportHour aqiReportHour) {
		StationAqiVO savo=new StationAqiVO();
		Factor factor=new Factor();
		WarpWeft wrapweft=new WarpWeft();
		factor.setAqi(StringUtil.valueToString(aqiReportHour.getAqiValue()))
		.setCo(StringUtil.valueToString(aqiReportHour.getCo()))
		.setNo2(StringUtil.valueToString(aqiReportHour.getNo2()))
		.setO3(StringUtil.valueToString(aqiReportHour.getO3()))
		.setSo2(StringUtil.valueToString(aqiReportHour.getSo2()))
		.setPm25(StringUtil.valueToString(aqiReportHour.getPm25()))
		.setPm10(StringUtil.valueToString(aqiReportHour.getPm10()));
		wrapweft.setLng(aqiReportHour.getLongitude())
		.setLat(aqiReportHour.getLatitude());
		savo.setId(aqiReportHour.getHourAqiId())
		.setAirVal(factor)
		.setPublishDateTime(aqiReportHour.getMonitorDate())
		.setGeo(wrapweft)
		.setMonitorType("")
		.setStationCode(aqiReportHour.getPointId())
		.setStationName(aqiReportHour.getPointName())
		.setStationType(aqiReportHour.getStandby4());
		return savo;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-统计报表数据(查询全是微站的数据)
	 * @Date 2019/11/21
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	@Override
	public List<StationReportVO> getStationReport(StationAqi stationAqi) {
		List<StationReportVO> list=new ArrayList<StationReportVO>();
		list =IntegrationStation(list, stationAqi);
		return list;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 整理返回数据
	 * @Date 2019/11/21
	 * @Params [list]
	 * @return List<StationAqi,listAr>
	 **/
	private List<StationReportVO> IntegrationStation(List<StationReportVO> list, StationAqi stationAqi) {
		AqiReportHour aqiReportHour=new AqiReportHour();
		aqiReportHour.setXzsqCode(stationAqi.getCityCode())
		.setStandby2(stationAqi.getStandby2())
		.setStandby3(stationAqi.getStandby3());
		List<AqiReportHour> listAr=aqiReportHourMapper.getAqiReportHourReport(aqiReportHour);
		if(null!=listAr&&listAr.size()>0){
			for(AqiReportHour arh:listAr){
				list.add(addStationAqi(arh));
			}
		}
		return list;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 整理返回数据
	 * @Date 2019/11/21
	 * @Params [list]
	 * @return List<StationAqi,listAr>
	 **/
	private StationReportVO addStationAqi(AqiReportHour arh) {
		StationReportVO stationReportVO=new StationReportVO();
		stationReportVO.setStationName(arh.getPointName())
		.setAqi(StringUtil.valueToString(arh.getAqiValue()))
		.setPm10(StringUtil.valueToString(arh.getPm10()))
		.setPm25(StringUtil.valueToString(arh.getPm25()))
		.setSo2(StringUtil.valueToString(arh.getSo2()))
		.setNo2(StringUtil.valueToString(arh.getNo2()))
		.setCo(StringUtil.valueToString(arh.getCo()))
		.setO3(StringUtil.valueToString(arh.getO3()));
		return stationReportVO;
	}

	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据
	 * @Date 2019/11/28
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	@Override
	public StationRatioVO getStationRatio(StationAqi stationAqi) {
		if(null==stationAqi.getStationCode()||null==stationAqi.getStationName()){
			return null;
		}
		// 国控、省控、市控站点信息
		StationRatioVO svo=new StationRatioVO();
		stationAqi.setStandby1(HourDayType.H.getValue());
		StationAqi thisList=stationAqiMapper.getStationRatio(stationAqi);
		stationAqi.setStandby1(HourDayType.LH.getValue());
		StationAqi lastList=stationAqiMapper.getStationRatio(stationAqi);
		if(null!=thisList||null!=lastList){
			svo=IntegrationStationAqi(svo,thisList,lastList);
		}
		// 微站信息
		AqiReportHour aqiReportHour=new AqiReportHour();
		aqiReportHour.setPointName(stationAqi.getStationName())
		.setPointId(stationAqi.getStationCode())
		.setStandby2(HourDayType.H.getValue())
		.setStandby3(stationAqi.getStandby5());
		AqiReportHour thisListAqi=aqiReportHourMapper.getAqiReportHourRatio(aqiReportHour);
		aqiReportHour.setStandby2(HourDayType.LH.getValue());
		AqiReportHour lastListAqi=aqiReportHourMapper.getAqiReportHourRatio(aqiReportHour);
		if(null!=thisListAqi||null!=lastListAqi){
			svo=IntegrationAqiReportHour(svo,thisListAqi,lastListAqi);
		}
		return svo;
	}

	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据数据处理
	 * @Date 2019/11/28
	 * @Params [svo,thisListAqi,lastListAqi]
	 * @return 
	 **/
	private StationRatioVO IntegrationAqiReportHour(StationRatioVO svo,AqiReportHour thisListAqi,AqiReportHour lastListAqi) {
		List<RatioVo> listRatio=new ArrayList<RatioVo>();
		listRatio=IntegrationRatioVo(listRatio,thisListAqi,lastListAqi);
		if(null!=thisListAqi){
			svo.setStationName(thisListAqi.getPointName());
		}else if(null!=lastListAqi){
			svo.setStationName(lastListAqi.getPointName());
		}
		svo.setFactor(listRatio);
		return svo;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据数据处理
	 * @Date 2019/11/28
	 * @Params [svo,thisList,lastList]
	 * @return 
	 **/
	private StationRatioVO IntegrationStationAqi(StationRatioVO svo,StationAqi thisList,StationAqi lastList) {
		List<RatioVo> listRatio=new ArrayList<RatioVo>();
		listRatio=IntegrationRatioVo(listRatio,thisList,lastList);
		if(null!=thisList){
			svo.setStationName(thisList.getStationName());
		}else if(null!=lastList){
			svo.setStationName(lastList.getStationName());
		}
		svo.setFactor(listRatio);
		return svo;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据数据处理
	 * @Date 2019/11/28
	 * @Params [listRatio,thisList,lastList]
	 * @return 
	 **/
	private List<RatioVo> IntegrationRatioVo(List<RatioVo> listRatio,AqiReportHour thisListAqi,AqiReportHour lastListAqi) {
		for(int e=0;e<Constants.FACTOR_COUNT;e++){
			listRatio.add(new RatioVo());
		}
		listRatio=IntegrationRatioList(listRatio,thisListAqi,lastListAqi);
		return listRatio;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据数据处理
	 * @Date 2019/11/28
	 * @Params [listRatio,thisList,lastList]
	 * @return 
	 **/
	private List<RatioVo> IntegrationRatioVo(List<RatioVo> listRatio,StationAqi thisList,StationAqi lastList) {
		for(int e=0;e<Constants.FACTOR_COUNT;e++){
			listRatio.add(new RatioVo());
		}
		listRatio=IntegrationRatioList(listRatio,thisList,lastList);
		return listRatio;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据数据处理
	 * @Date 2019/11/28
	 * @Params [listRatio,thisList,lastList]
	 * @return 
	 **/
	private List<RatioVo> IntegrationRatioList(List<RatioVo> listRatio,
			AqiReportHour thisList, AqiReportHour lastList) {
		if(null!=thisList&&null!=lastList){
			listRatio.get(0).setType(Constants.FACTOR_PM10).setValue(StringUtil.valueToString(thisList.getPm10())).setRaito(StringUtil.valueToString(thisList.getPm10()-lastList.getPm10()));
			listRatio.get(1).setType(Constants.FACTOR_PM25).setValue(StringUtil.valueToString(thisList.getPm25())).setRaito(StringUtil.valueToString(thisList.getPm25()-lastList.getPm25()));
			listRatio.get(2).setType(Constants.FACTOR_SO2).setValue(StringUtil.valueToString(thisList.getSo2())).setRaito(StringUtil.valueToString(thisList.getSo2()-lastList.getSo2()));
			listRatio.get(3).setType(Constants.FACTOR_NO2).setValue(StringUtil.valueToString(thisList.getNo2())).setRaito(StringUtil.valueToString(thisList.getNo2()-lastList.getNo2()));
			listRatio.get(4).setType(Constants.FACTOR_CO).setValue(StringUtil.valueToString(thisList.getCo())).setRaito(StringUtil.valueToString(thisList.getCo()-lastList.getCo()));
			listRatio.get(5).setType(Constants.FACTOR_O3).setValue(StringUtil.valueToString(thisList.getO3())).setRaito(StringUtil.valueToString(thisList.getO3()-lastList.getO3()));
		}else if(null!=thisList&&null==lastList){
			listRatio.get(0).setType(Constants.FACTOR_PM10).setValue(StringUtil.valueToString(thisList.getPm10())).setRaito(StringUtil.valueToString(thisList.getPm10()-0));
			listRatio.get(1).setType(Constants.FACTOR_PM25).setValue(StringUtil.valueToString(thisList.getPm25())).setRaito(StringUtil.valueToString(thisList.getPm25()-0));
			listRatio.get(2).setType(Constants.FACTOR_SO2).setValue(StringUtil.valueToString(thisList.getSo2())).setRaito(StringUtil.valueToString(thisList.getSo2()-0));
			listRatio.get(3).setType(Constants.FACTOR_NO2).setValue(StringUtil.valueToString(thisList.getNo2())).setRaito(StringUtil.valueToString(thisList.getNo2()-0));
			listRatio.get(4).setType(Constants.FACTOR_CO).setValue(StringUtil.valueToString(thisList.getCo())).setRaito(StringUtil.valueToString(thisList.getCo()-0));
			listRatio.get(5).setType(Constants.FACTOR_O3).setValue(StringUtil.valueToString(thisList.getO3())).setRaito(StringUtil.valueToString(thisList.getO3()-0));
		}else if(null==thisList&&null!=lastList){
			listRatio.get(0).setType(Constants.FACTOR_PM10).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getPm10()));
			listRatio.get(1).setType(Constants.FACTOR_PM25).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getPm25()));
			listRatio.get(2).setType(Constants.FACTOR_SO2).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getSo2()));
			listRatio.get(3).setType(Constants.FACTOR_NO2).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getNo2()));
			listRatio.get(4).setType(Constants.FACTOR_CO).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getCo()));
			listRatio.get(5).setType(Constants.FACTOR_O3).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getO3()));
		}
		return listRatio;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据数据处理
	 * @Date 2019/11/28
	 * @Params [listRatio,thisList,lastList]
	 * @return 
	 **/
	private List<RatioVo> IntegrationRatioList(List<RatioVo> listRatio,
			StationAqi thisList, StationAqi lastList) {
		if(null!=thisList&&null!=lastList){
			listRatio.get(0).setType(Constants.FACTOR_PM10).setValue(StringUtil.valueToString(thisList.getPm10())).setRaito(StringUtil.valueToString(thisList.getPm10()-lastList.getPm10()));
			listRatio.get(1).setType(Constants.FACTOR_PM25).setValue(StringUtil.valueToString(thisList.getPm25())).setRaito(StringUtil.valueToString(thisList.getPm25()-lastList.getPm25()));
			listRatio.get(2).setType(Constants.FACTOR_SO2).setValue(StringUtil.valueToString(thisList.getSo2())).setRaito(StringUtil.valueToString(thisList.getSo2()-lastList.getSo2()));
			listRatio.get(3).setType(Constants.FACTOR_NO2).setValue(StringUtil.valueToString(thisList.getNo2())).setRaito(StringUtil.valueToString(thisList.getNo2()-lastList.getNo2()));
			listRatio.get(4).setType(Constants.FACTOR_CO).setValue(StringUtil.valueToString(thisList.getCo())).setRaito(StringUtil.valueToString(thisList.getCo()-lastList.getCo()));
			listRatio.get(5).setType(Constants.FACTOR_O3).setValue(StringUtil.valueToString(thisList.getO3())).setRaito(StringUtil.valueToString(thisList.getO3()-lastList.getO3()));
		}else if(null!=thisList&&null==lastList){
			listRatio.get(0).setType(Constants.FACTOR_PM10).setValue(StringUtil.valueToString(thisList.getPm10())).setRaito(StringUtil.valueToString(thisList.getPm10()-0));
			listRatio.get(1).setType(Constants.FACTOR_PM25).setValue(StringUtil.valueToString(thisList.getPm25())).setRaito(StringUtil.valueToString(thisList.getPm25()-0));
			listRatio.get(2).setType(Constants.FACTOR_SO2).setValue(StringUtil.valueToString(thisList.getSo2())).setRaito(StringUtil.valueToString(thisList.getSo2()-0));
			listRatio.get(3).setType(Constants.FACTOR_NO2).setValue(StringUtil.valueToString(thisList.getNo2())).setRaito(StringUtil.valueToString(thisList.getNo2()-0));
			listRatio.get(4).setType(Constants.FACTOR_CO).setValue(StringUtil.valueToString(thisList.getCo())).setRaito(StringUtil.valueToString(thisList.getCo()-0));
			listRatio.get(5).setType(Constants.FACTOR_O3).setValue(StringUtil.valueToString(thisList.getO3())).setRaito(StringUtil.valueToString(thisList.getO3()-0));
		}else if(null==thisList&&null!=lastList){
			listRatio.get(0).setType(Constants.FACTOR_PM10).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getPm10()));
			listRatio.get(1).setType(Constants.FACTOR_PM25).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getPm25()));
			listRatio.get(2).setType(Constants.FACTOR_SO2).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getSo2()));
			listRatio.get(3).setType(Constants.FACTOR_NO2).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getNo2()));
			listRatio.get(4).setType(Constants.FACTOR_CO).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getCo()));
			listRatio.get(5).setType(Constants.FACTOR_O3).setValue(StringUtil.valueToString(0)).setRaito(StringUtil.valueToString(0-lastList.getO3()));
		}
		return listRatio;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 获取返回所有站点名称和code
	 * @Date 10:352019/11/29
	 * @Params stationAqi
	 * @return List<StationVO>
	 **/
	@Override
	public List<StationVO> getStationInfo(StationAqi stationAqi) {
		if(isNUllCityCode(stationAqi)){
			return null;
		}
		List<StationVO> listStationVo=stationAqiMapper.getStationInfo(stationAqi);
		List<StationVO> listStationAqiVo=aqiReportHourMapper.getStationInfo();
		if(null!=listStationAqiVo&&listStationAqiVo.size()>0){
			for(StationVO svo:listStationAqiVo){
				listStationVo.add(svo);
			}
		}
		return listStationVo;
	}
}

