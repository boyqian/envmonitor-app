package com.upsoft.environmentmonitoring.domain.vo;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName StationReportVO
 * @Description 统计分析-站点统计报表返回实体
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class StationReportVO {

	/**
     * @Description: stationName
     */
	private String stationName;
	
	/**
     * @Description: aqi
     */
    private String aqi;
    
    /**
     * @Description: so2
     */
    private String so2;
    
    /**
     * @Description: co
     */
    private String co;
    
    /**
     * @Description: no2
     */
    private String no2;
    
    /**
     * @Description: o3
     */
    private String o3;
    
    /**
     * @Description: pm25
     */
    private String pm25;
    
    /**
     * @Description: pm10
     */
    private String pm10;
}
