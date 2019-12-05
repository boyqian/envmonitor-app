package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName PrimaryPollutant
 * @Description 首页-近一小时主要污染物
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class PrimaryPollutant implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
     * @Description: aqi
     */
    private Integer aqi;
    
    /**
     * @Description: aqiLevel等级
     */
    private String aqiLevel;
    
    /**
     * @Description: primaryPollutant首要污染物
     */
    private String primaryPollutant;
    
    /**
     * @Description: 更新时间
     */
    private LocalDateTime publishDateTime;

}
