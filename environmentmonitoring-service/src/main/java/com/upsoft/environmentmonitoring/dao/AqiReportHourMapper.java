package com.upsoft.environmentmonitoring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.upsoft.environmentmonitoring.domain.po.AqiReportHour;
import com.upsoft.environmentmonitoring.domain.vo.StationVO;

/**
 * @ClassName AqiReportHourMapper
 * @Description 微站点的各个因子的数据
 * @Author Wang wanqian
 * @Date 2019-11-18
 * @Version 1.0
 */
@Repository
@DS("aqm")
public interface AqiReportHourMapper {
	
	/**
	 * @Author Wang wanqian
	 * @Description 根据所在城市code查询微站点分布
	 * @Date 2019-11-18
	 * @Params [aqiReportHour]
	 * @return List<AqiReportHour>
	 **/
	List<AqiReportHour> getAqiReportByOperation(AqiReportHour aqiReportHour);
	
	/**
	 * @Author Wang wanqian
	 * @Description 根据所在城市code查询单个微站点折线图
	 * @Date 2019-11-18
	 * @Params [aqiReportHour]
	 * @return List<AqiReportHour>
	 **/
	List<AqiReportHour> getSingleAqiReportThrend(AqiReportHour aqiReportHour);
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-显示微站的统计报表
	 * @Date 2019-11-21
	 * @Params [aqiReportHour]
	 * @return List<AqiReportHour>
	 **/
	List<AqiReportHour> getAqiReportHourReport(AqiReportHour aqiReportHour);
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据
	 * @Date 2019-11-21
	 * @Params [aqiReportHour]
	 * @return AqiReportHour
	 **/
	AqiReportHour getAqiReportHourRatio(AqiReportHour aqiReportHour);
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取返回所有站点名称和code
	 * @Date 11:33 2019/11/29
	 * @Params stationAqi
	 * @return List<StationVO>
	 **/
	List<StationVO> getStationInfo();
	
	/**
	 * @Author Wang wanqian
	 * @Description 查询最新一条数据的时间点
	 * @Date 15:33 2019/12/02
	 * @Params 
	 * @return String
	 **/
	String getNewestTime();
}
