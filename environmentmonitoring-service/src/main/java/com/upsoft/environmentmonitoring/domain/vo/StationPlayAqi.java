package com.upsoft.environmentmonitoring.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName StationPlayAqi
 * @Description 监测地图24小时播放数据返回实体
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class StationPlayAqi {

	/**
     * @Description: airVal 各个因子的值
     */
	private Factor airVal;
	
	/**
     * @Description: geo经纬度
     */
	private WarpWeft geo;
	
	/**
     * @Description: geo经纬度
     */
	private String stationCode;
	
	/**
     * @Description: id
     */
    private String id;
    
    /**
     * @Description: stationName
     */
    private String stationName;
    
    /**
     * @Description: stationType
     */
    private String stationType;
    
}
