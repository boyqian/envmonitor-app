package com.upsoft.environmentmonitoring.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.domain.vo.StationAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.StationPlayAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.StationRatioVO;
import com.upsoft.environmentmonitoring.domain.vo.StationReportVO;
import com.upsoft.environmentmonitoring.domain.vo.StationVO;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.service.StationAqiService;
import com.upsoft.environmentmonitoring.service.StationPlayAqiService;
import com.upsoft.environmentmonitoring.service.impl.StationVideoServiceImpl;

import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StationAqiController
 * @Description StationAqi Restful API接口
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@RestController
@Validated
@Api(value = "StationAqi管理接口")
public class StationAqiController {
   /**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入StationAqiService
	 */
	@Resource(name = "stationAqiServiceImpl")
	private StationAqiService stationAqiService;
	
	/**
	 * @Description: 注入stationPlayAqiService
	 */
	@Resource(name = "stationPlayAqiServiceImpl")
	private StationPlayAqiService stationPlayAqiService;

	/**
	 * @Description: 注入stationPlayAqiService
	 */
	@Resource(name = "stationVideoServiceImpl")
	private StationVideoServiceImpl stationVideoServiceImpl;
	
	/**
	 * @Description: 注入HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * @Author Wang wanqian
	 * @Description 获取监测地图站点分布情况
	 * @Date 2019/11/15
	 * @Params [stationAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/stationAqi/getStationAqiSpread")
	@ApiOperation(value = "获取监测地图站点分布情况",notes="必传参数城市cityCode,standby1不传默认或者传\"H\"为近1小时,传\"D\"表示24小时数据播放,standby2不传默认最新的时间点，传参形式如\"2019-12-02 14:00:00\"时间点")
	public BaseResult getStationAqiOperation(@RequestBody StationAqi stationAqi){
		if(null==stationAqi.getStandby1()||stationAqi.getStandby1().equals("H")){
			List<StationAqiVO> list=stationVideoServiceImpl.getStationVideo(stationAqi);
			return BaseResultUtil.success(list);
		}else{
			List<StationPlayAqiVO> list=stationPlayAqiService.request(stationAqi);
			return BaseResultUtil.success(list);
		}
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 监测地图-单个站点不同因子不同时间段折线图
	 * @Date 2019/11/15
	 * @Params [stationAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/stationAqi/getSingleStationAqiThrend")
	@ApiOperation(value = "监测地图-单个站点不同因子不同时间段折线图",notes="必传参数城市cityCode,站点stationCode,standby2开始时间，standby3结束时间,可传参数stationType(国控、市控、微站代码)")
	public BaseResult getSingleStationAqiThrend(@RequestBody StationAqi stationAqi){
		List<StationAqiVO> list=stationAqiService.getSingleStationAqiThrend(stationAqi);
		return BaseResultUtil.success(list);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-统计报表数据
	 * @Date 2019/11/21
	 * @Params [stationAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/stationAqi/getStationReport")
	@ApiOperation(value = "统计分析-统计报表数据",notes="必传参数城市cityCode,standby2开始时间，standby3结束时间")
	public BaseResult getStationReport(@RequestBody StationAqi stationAqi){
		List<StationReportVO> list=stationAqiService.getStationReport(stationAqi);
		return BaseResultUtil.success(list);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据
	 * @Date 2019/11/28
	 * @Params [stationAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/stationAqi/getStationRatio")
	@ApiOperation(value = "统计分析-站点对比数据",notes="必传参数站点stationCode,stationName,standby5时间,字符串类型,如:\"20191101\"")
	public BaseResult getStationRatio(@RequestBody StationAqi stationAqi){
		StationRatioVO svo=stationAqiService.getStationRatio(stationAqi);
		return BaseResultUtil.success(svo);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取返回所有站点名称和code
	 * @Date 10:40 2019/11/29
	 * @Params stationAqi
	 * @return List<StationVO>
	 **/
	@PostMapping("/stationAqi/getStationInfo")
	@ApiOperation(value = "获取返回所有站点名称和code",notes="必传参数城市cityCode")
	public BaseResult getStationInfo(@RequestBody StationAqi stationAqi){
		List<StationVO> listVo=stationAqiService.getStationInfo(stationAqi);
		return BaseResultUtil.success(listVo);
	}
}
