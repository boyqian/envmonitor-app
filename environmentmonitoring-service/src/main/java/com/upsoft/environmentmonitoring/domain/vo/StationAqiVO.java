package com.upsoft.environmentmonitoring.domain.vo;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName StationAqiVO
 * @Description 监测地图返回实体
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class StationAqiVO {
	
	/**
     * @Description: id
     */
    private String id;
    
    /**
     * @Description: stationName
     */
    private String stationName;
    
    /**
     * @Description: monitorType
     */
    private String monitorType;
    
    /**
     * @Description: airVal
     */
    private Factor airVal;
    
    /**
     * @Description: stationType
     */
    private String stationType;
    
    /**
     * @Description: geo
     */
    private WarpWeft geo;
    
    /**
     * @Description: 关联videoId
     */
    private String videoId;
    
    /**
     * @Description: stationCode
     */
    private String stationCode;
    
    /**
     * @Description: 是否显示
     */
    private boolean show=true;
    
    /**
     * @Description: 最新更新时间
     */
    private  LocalDateTime publishDateTime;
}
