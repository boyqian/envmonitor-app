package com.upsoft.environmentmonitoring.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import com.upsoft.environmentmonitoring.domain.po.CityAqi;
import com.upsoft.environmentmonitoring.domain.po.TargetDays;
import com.upsoft.environmentmonitoring.domain.vo.GoodDays;
import com.upsoft.environmentmonitoring.domain.vo.PrimaryPollutant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName CityAqiMapper
 * @Description 城市AQI和6项因子的小时监测数据和天监测数据 Mapper 接口
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@Repository
@DS("environmentmonitoring")
public interface CityAqiMapper extends BaseMapper<CityAqi> {

	/**
	 * @Author Wei Wei
	 * @Description 批量新增CityAqi
	 * @Date 2019-11-01
	 * @Params [List<CityAqi>]
	 * @return void
	 **/
	void insertCityAqiBatch(@Param("cityAqiList") List<CityAqi> cityAqiList);

	/**
	 * @Author weiwei
	 * @Description 获取四川省城市AQI数据最后更新时间
	 * @Date 15:26 2019/11/4
	 * @Params [String cityAqiType]
	 * @return java.time.LocalDateTime
	 **/
	LocalDateTime getLatestTimeForCityAqi(@Param("cityAqiType") String cityAqiType);
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取首页目标天数和达标天数
	 * @Date 14:03 2019/11/12
	 * @Params [cityAqi]
	 * @return TargetDays
	 **/
	TargetDays getTargetDays(CityAqi cityAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 最近24小时或30天的各个因子的数据
	 * @Date 11:00 2019/11/15
	 * @Params [cityAqi]
	 * @return List<CityAqi>
	 **/
	List<CityAqi> getCityAqiByOperation(CityAqi cityAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 最近本年或本月的优良天数数据
	 * @Date 14:19 2019/11/15
	 * @Params [cityAqi]
	 * @return GoodDays
	 **/
	GoodDays getGoodDaysByOperation(CityAqi cityAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 首页-今日最近一个小时的AQI数据及主要污染物
	 * @Date 14:19 2019/11/19
	 * @Params [cityAqi]
	 * @return PrimaryPollutant
	 **/
	PrimaryPollutant getPrimaryPollutant(CityAqi cityAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-根据城市code和选择年月获取Aqi日历
	 * @Date 14:19 2019/11/20
	 * @Params [cityAqi]
	 * @return List<CityAqi>
	 **/
	List<CityAqi> getAqiCalendar(CityAqi cityAqi);
}
