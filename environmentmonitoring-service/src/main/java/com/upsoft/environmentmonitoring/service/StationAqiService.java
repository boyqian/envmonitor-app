package com.upsoft.environmentmonitoring.service;

import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.StationAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.StationRatioVO;
import com.upsoft.environmentmonitoring.domain.vo.StationReportVO;
import com.upsoft.environmentmonitoring.domain.vo.StationVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName StationAqiMapper
 * @Description 监测站点AQI和6项因子的小时监测数据和天监测数据服务接口
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
public interface StationAqiService {

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
	 * @return List<StationAqiVO>
	 **/
	List<StationAqiVO> getStationAqiByOperation(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 监测地图-单个站点不同因子不同时间段折线图
	 * @Date 2019/11/15
	 * @Params [stationAqi]
	 * @return List<StationAqiVO>
	 **/
	List<StationAqiVO> getSingleStationAqiThrend(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-统计报表数据
	 * @Date 2019/11/21
	 * @Params [stationAqi]
	 * @return List<StationReportVO>
	 **/
	List<StationReportVO> getStationReport(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 统计分析-站点对比数据
	 * @Date 2019/11/28
	 * @Params [stationAqi]
	 * @return StationRatioVO
	 **/
	StationRatioVO getStationRatio(StationAqi stationAqi);
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取返回所有站点名称和code
	 * @Date 10:33 2019/11/29
	 * @Params stationAqi
	 * @return List<StationVO>
	 **/
	List<StationVO> getStationInfo(StationAqi stationAqi);
	
}
