package com.upsoft.environmentmonitoring.controller;

import java.util.List;

import javax.annotation.Resource;

import com.upsoft.environmentmonitoring.domain.vo.AqiCalendarVO;
import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.domain.vo.CityAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.GoodDays;
import com.upsoft.environmentmonitoring.domain.vo.PrimaryPollutant;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.upsoft.environmentmonitoring.domain.po.CityAqi;
import com.upsoft.environmentmonitoring.domain.po.TargetDays;
import com.upsoft.environmentmonitoring.service.CityAqiHourDayService;
import com.upsoft.environmentmonitoring.service.CityAqiService;

import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CityAqiController
 * @Description CityAqi Restful API接口
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@RestController
@Validated
@Api(value = "CityAqi管理接口")
public class CityAqiController {
   /**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入CityAqiService
	 */
	@Resource(name = "cityAqiServiceImpl")
	private CityAqiService cityAqiService;
	
	/**
	 * @Description: 注入CityAqiHourDayService
	 */
	@Resource(name = "cityAqiHourDayServiceImpl")
	private CityAqiHourDayService cityAqiHourDayService;
	
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取首页达标天数
	 * @Date 17:43 2019/11/13
	 * @Params [cityAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/cityAqi/getTargetDays")
	@ApiOperation(value = "获取首页达标天数",notes="必传参数城市cityCode")
	public BaseResult getTargetDays(@RequestBody CityAqi cityAqi){
		TargetDays target=cityAqiService.getTargetDays(cityAqi);
		return BaseResultUtil.success(target);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 最近24小时或30天的各个因子的数据
	 * @Date 11:13 2019/11/15
	 * @Params [cityAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/cityAqi/getHourDayStatistics")
	@ApiOperation(value = "最近24小时或30天的各个因子的数据",notes="必传参数cityCode,cityAqiType不传默认或者传\"H\"为近24小时,传\"D\"表示近30天数据")
	public BaseResult getProblemByOperation(@RequestBody CityAqi cityAqi){
		List<CityAqiVO> list=cityAqiHourDayService.getCityAqiHourDay(cityAqi);
		return BaseResultUtil.success(list);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 首页查询本年或本月的优良天数数据
	 * @Date 15:04 2019/11/15
	 * @Params [cityAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/cityAqi/getGoodDayStatistics")
	@ApiOperation(value = "查询本年或本月的优良天数数据",notes="必传参数cityCode,cityAqiType不传默认或者传\"D\"为本年,传\"H\"表示本月数据")
	public BaseResult getGoodDaysByOperation(@RequestBody CityAqi cityAqi){
		GoodDays goodDays=cityAqiService.getGoodDaysByOperation(cityAqi);
		return BaseResultUtil.success(goodDays);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 首页-今日最近的AQI数据及主要污染物
	 * @Date 14:19 2019/11/19
	 * @Params [cityAqi]
	 * @return BaseResult
	 **/
	@PostMapping("/cityAqi/getPrimaryPollutant")
	@ApiOperation(value = "今日最近的AQI数据及主要污染物",notes="必传参数cityCode")
	public BaseResult getPrimaryPollutant(@RequestBody CityAqi cityAqi){
		PrimaryPollutant proPoll=cityAqiService.getPrimaryPollutant(cityAqi);
		return BaseResultUtil.success(proPoll);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-根据城市code和选择年月获取Aqi日历
	 * @Date 14:19 2019/11/20
	 * @Params [cityAqi]
	 * @return PrimaryPollutant
	 **/
	@PostMapping("/cityAqi/getAqiCalendar")
	@ApiOperation(value = "根据城市code和选择年月获取Aqi日历",notes="必传参数cityCode和standby1传表示日期\"2019-11-01\"格式，显示某市某月的aqi日历信息")
	public BaseResult getAqiCalendar(@RequestBody CityAqi cityAqi){
		List<AqiCalendarVO> list=cityAqiService.getAqiCalendar(cityAqi);
		return BaseResultUtil.success(list);
	}
}
