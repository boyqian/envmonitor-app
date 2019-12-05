package com.upsoft.environmentmonitoring.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.StationReportVO;
import com.upsoft.environmentmonitoring.domain.vo.StationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName StationAqiMapper
 * @Description 监测站点AQI和6项因子的小时监测数据和天监测数据 Mapper 接口
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@Repository
@DS("environmentmonitoring")
public interface StationAqiMapper extends BaseMapper<StationAqi> {

	/**
	 * @Author Wei Wei
	 * @Description 批量新增StationAqi
	 * @Date 2019-11-01
	 * @Params [List<StationAqi>]
	 * @return void
	 **/
	void insertStationAqiBatch(@Param("stationAqiList") List<StationAqi> stationAqiList);

	/**
	 * @Author Wei Wei
	 * @Description 查询单个StationAqi
	 * @Date 2019-11-01
	 * @Params [StationAqi]
	 * @return StationAqi
	 **/
    StationAqi getStationAqi(StationAqi stationAqi);

	/**
	 * @Author weiwei
	 * @Description 获取四川省监测站点AQI数据最后更新时间
	 * @Date 2019/11/6
	 * @Params [stationAqiType]
	 * @return java.time.LocalDateTime
	 **/
	LocalDateTime getLatestTimeForStationAqi(@Param("stationAqiType") String stationAqiType);

	/**
	 * @Author Wang wanqian
	 * @Description 获取监测地图站点分布情况
	 * @Date 2019/11/15
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	List<StationAqi> getStationAqiByOperation(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 监测地图-单个站点不同因子不同时间段折线图
	 * @Date 2019/11/15
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	List<StationAqi> getSingleStationAqiThrend(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-统计报表数据
	 * @Date 2019/11/20
	 * @Params [stationAqi]
	 * @return List<StationAqi>
	 **/
	List<StationReportVO> getStationReport(StationAqi stationAqi);
	
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点比对，环比（上一个月对比）
	 * @Date 16:33 2019/11/28
	 * @Params [stationAqi]
	 * @return StationAqi
	 **/
	StationAqi getStationRatio(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取返回所有站点名称和code
	 * @Date 10:33 2019/11/29
	 * @Params stationAqi
	 * @return List<StationVO>
	 **/
	List<StationVO> getStationInfo(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 查询最新一条数据的时间点
	 * @Date 15:33 2019/12/02
	 * @Params 
	 * @return String
	 **/
	String getNewestTime();
}
