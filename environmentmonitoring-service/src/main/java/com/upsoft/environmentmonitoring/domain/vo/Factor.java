package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Factor
 * @Description 各种因子实体
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class Factor implements Serializable{

	private static final long serialVersionUID = 1L;

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
