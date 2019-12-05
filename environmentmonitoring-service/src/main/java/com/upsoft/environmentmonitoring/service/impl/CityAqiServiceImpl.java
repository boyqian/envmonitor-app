package com.upsoft.environmentmonitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.constant.HourDayType;
import com.upsoft.environmentmonitoring.domain.po.CityAqi;
import com.upsoft.environmentmonitoring.domain.po.TargetDays;
import com.upsoft.environmentmonitoring.domain.vo.AqiCalendarVO;
import com.upsoft.environmentmonitoring.domain.vo.CalendarFactor;
import com.upsoft.environmentmonitoring.domain.vo.GoodDays;
import com.upsoft.environmentmonitoring.domain.vo.PrimaryPollutant;
import com.upsoft.environmentmonitoring.dao.CityAqiMapper;
import com.upsoft.environmentmonitoring.service.CityAqiService;
import com.upsoft.environmentmonitoring.utils.DateUtil;
import com.upsoft.environmentmonitoring.utils.StringUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CityAqiServiceImpl
 * @Description 城市AQI和6项因子的小时监测数据和天监测数据 服务实现类
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@Service("cityAqiServiceImpl")
public class CityAqiServiceImpl implements CityAqiService {

   /**
	 * @Description: 注入CityAqiMapper
	 */
	@Autowired
	private CityAqiMapper cityAqiMapper;

	/**
	 * @Author weiwei
	 * @Description 获取四川省城市AQI小时数据最后更新时间
	 * @Date 15:26 2019/11/4
	 * @Params [String cityAqiType]
	 * @return java.time.LocalDateTime
	 **/
	@Override
	public LocalDateTime getLatestTimeForCityAqi(String cityAqiType) {
		return cityAqiMapper.getLatestTimeForCityAqi(cityAqiType);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取首页目标天数和达标天数
	 * @Date 14:05 2019/11/12
	 * @Params [cityAqi]
	 * @return TargetDays
	 **/
	@Override
	public TargetDays getTargetDays(CityAqi cityAqi) {
		if(isNullCityCode(cityAqi)){
			return null;
		}else{
			return cityAqiMapper.getTargetDays(cityAqi);
		}
	}

	/**
	 * @Author Wang wanqian
	 * @Description 最近24小时或30天的各个因子的数据,不传参数cityAqiType默认为24小时
	 * @Date 11:03 2019/11/15
	 * @Params [cityAqi]
	 * @return List<CityAqi>
	 **/
	@Override
	public List<CityAqi> getProblemByOperation(CityAqi cityAqi) {
		if(isNullCityCode(cityAqi)){
			return null;
		}else{
			return cityAqiMapper.getCityAqiByOperation(cityAqi);
		}
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 判断城市code是否为空,是则返回true,否则返回false
	 * @Date 11:13 2019/11/15
	 * @Params [cityAqi]
	 * @return boolean
	 **/
	private boolean isNullCityCode(CityAqi cityAqi){
		if(null==cityAqi.getCityCode()||cityAqi.getCityCode().equals("")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Author Wang wanqian
	 * @Description 首页查询本年或本月的优良天数数据
	 * @Date 15:04 2019/11/15
	 * @Params [cityAqi]
	 * @return GoodDays
	 **/
	@Override
	public GoodDays getGoodDaysByOperation(CityAqi cityAqi) {
		GoodDays goodDays=null;
		if(isNullCityCode(cityAqi)){
			return null;
		}else{
			goodDays=new GoodDays();
			goodDays=getYearOnMonth(goodDays,cityAqi);
			return goodDays;
		}
		
	}

	/**
	 * @Author Wang wanqian
	 * @Description 首页-增加同比环比数据
	 * @Date 14:19 2019/11/19
	 * @Params [goodDays,cityAqi]
	 * @return GoodDays
	 **/
	private GoodDays getYearOnMonth(GoodDays goodDays,CityAqi cityAqi) {
		GoodDays thisGoodDays=cityAqiMapper.getGoodDaysByOperation(cityAqi);
		String cityAqiType=cityAqi.getCityAqiType();
		String lastCityAqiType=HourDayType.LD.getValue();
		if(null!=cityAqiType&&!cityAqiType.equals("")){
			if(cityAqiType.equals(HourDayType.H.getValue())){
				lastCityAqiType=HourDayType.LH.getValue();
			}else{
				lastCityAqiType=HourDayType.LD.getValue();
			}
		}
		cityAqi.setCityAqiType(lastCityAqiType);
		GoodDays lastGoodDays=cityAqiMapper.getGoodDaysByOperation(cityAqi);
		goodDays.setExcellent(thisGoodDays.getExcellent())
		.setGood(thisGoodDays.getGood())
		.setLight(thisGoodDays.getLight())
		.setModerate(thisGoodDays.getModerate())
		.setSevere(thisGoodDays.getSevere())
		.setSerious(thisGoodDays.getSerious())
		.setExcellentDays(thisGoodDays.getExcellent()-lastGoodDays.getExcellent())
		.setGoodDays(thisGoodDays.getGood()-lastGoodDays.getGood())
		.setLightDays(thisGoodDays.getLight()-lastGoodDays.getLight())
		.setModerateDays(thisGoodDays.getModerate()-lastGoodDays.getModerate())
		.setSevereDays(thisGoodDays.getSevere()-lastGoodDays.getSevere())
		.setSeriousDays(thisGoodDays.getSerious()-lastGoodDays.getSerious());
		return goodDays;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 首页-今日最近一个小时的AQI数据及主要污染物
	 * @Date 14:19 2019/11/19
	 * @Params [cityAqi]
	 * @return PrimaryPollutant
	 **/
	@Override
	public PrimaryPollutant getPrimaryPollutant(CityAqi cityAqi) {
		if(isNullCityCode(cityAqi)){
			return null;
		}else{
			return cityAqiMapper.getPrimaryPollutant(cityAqi);
		}
	}

	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-根据城市code和选择年月获取Aqi日历
	 * @Date 14:19 2019/11/20
	 * @Params [cityAqi]
	 * @return List<AqiCalendarVO>
	 **/
	@Override
	public List<AqiCalendarVO> getAqiCalendar(CityAqi cityAqi) {
		if(isNullCityCode(cityAqi)&&isNullTime(cityAqi)){
			return null;
		}else{
			int dayOfMonth=DateUtil.howDayMonth(cityAqi.getStandby1());
			List<AqiCalendarVO> list=null;
			List<CityAqi> listCityAqi=cityAqiMapper.getAqiCalendar(cityAqi);
			if(null!=listCityAqi&&listCityAqi.size()>0){
				list=new ArrayList<AqiCalendarVO>(dayOfMonth);
				list=IntegrationStation(dayOfMonth,list,listCityAqi);
			}
			return list;
		}
	}
	
	
	/**
	 * @Author Wang wanqian
	 * @Description 判断传递的时间是否为空，否则没法返回日历列表信息
	 * @Date 10:13 2019/11/19
	 * @Params [cityAqi]
	 * @return boolean
	 **/
	private boolean isNullTime(CityAqi cityAqi) {
		if(null==cityAqi.getStandby1()||cityAqi.getStandby1().equals("")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Author Wang wanqian
	 * @Description 整合返回实体的数据
	 * @Date 10:13 2019/11/19
	 * @Params [list，listCityAqi]
	 * @return 
	 **/
	private List<AqiCalendarVO> IntegrationStation(Integer dayofMonth,List<AqiCalendarVO> list,List<CityAqi> listCityAqi) {
		for(int k=0;k<dayofMonth;k++){
			AqiCalendarVO acvo=new AqiCalendarVO();
			acvo.setDayNum(k+1).setAQI("").setMainPollutant("");
			List<CalendarFactor> listCafa=new ArrayList<CalendarFactor>(Constants.FACTOR_COUNT);
			listCafa=IntegrationListCafa(listCafa);
			acvo.setPollutant(listCafa);
			list.add(acvo);
		}
		for(CityAqi cityAqi:listCityAqi){
			int whichDaysofMonth=DateUtil.whichDayMonth(cityAqi.getPublishDateTime());
			list.set(whichDaysofMonth-1,addAqiCalendarVO(whichDaysofMonth,cityAqi));
		}
		return list;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 初始化返回数据
	 * @Date 14:13 2019/12/04
	 * @Params [listCafa]
	 * @return 
	 **/
	private List<CalendarFactor> IntegrationListCafa(List<CalendarFactor> listCafa) {
		for(int i=0;i<Constants.FACTOR_COUNT;i++){
			listCafa.add(new CalendarFactor());
		}
		listCafa.get(0).setType(Constants.FACTOR_PM10);
		listCafa.get(1).setType(Constants.FACTOR_PM25);
		listCafa.get(2).setType(Constants.FACTOR_SO2);
		listCafa.get(3).setType(Constants.FACTOR_NO2);
		listCafa.get(4).setType(Constants.FACTOR_CO);
		listCafa.get(5).setType(Constants.FACTOR_O3);
		return listCafa;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 整合返回实体的数据
	 * @Date 10:13 2019/11/19
	 * @Params [whichDaysofMonth,cityAqi]
	 * @return 
	 **/
	private AqiCalendarVO addAqiCalendarVO(Integer whichDaysofMonth, CityAqi cityAqi) {
		AqiCalendarVO acvo=new AqiCalendarVO();
		List<CalendarFactor> listCafa=new ArrayList<CalendarFactor>();
		listCafa=getCalendarFactor(listCafa,cityAqi);
		acvo.setAQI(StringUtil.valueToString(cityAqi.getAqi()))
		.setDayNum(whichDaysofMonth)
		.setMainPollutant(cityAqi.getPrimaryPollutant())
		.setPollutant(listCafa);
		return acvo;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 整合返回实体的数据
	 * @Date 10:13 2019/11/19
	 * @Params [listCafa,cityAqi]
	 * @return List<CalendarFactor>
	 **/
	private List<CalendarFactor> getCalendarFactor(List<CalendarFactor> listCafa, CityAqi cityAqi) {
		for(int i=0;i<Constants.FACTOR_COUNT;i++){
			listCafa.add(new CalendarFactor());
		}
		listCafa.get(0).setType(Constants.FACTOR_PM10).setValue(StringUtil.valueToString(cityAqi.getPm10()));
		listCafa.get(1).setType(Constants.FACTOR_PM25).setValue(StringUtil.valueToString(cityAqi.getPm25()));
		listCafa.get(2).setType(Constants.FACTOR_SO2).setValue(StringUtil.valueToString(cityAqi.getSo2()));
		listCafa.get(3).setType(Constants.FACTOR_NO2).setValue(StringUtil.valueToString(cityAqi.getNo2()));
		listCafa.get(4).setType(Constants.FACTOR_CO).setValue(StringUtil.valueToString(cityAqi.getCo()));
		listCafa.get(5).setType(Constants.FACTOR_O3).setValue(StringUtil.valueToString(cityAqi.getO3()));
		return listCafa;
	}

}

