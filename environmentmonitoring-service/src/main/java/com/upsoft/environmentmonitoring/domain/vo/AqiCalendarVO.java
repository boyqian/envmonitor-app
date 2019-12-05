package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName AqiCalendarVO
 * @Description Aqi日历前端返回实体类
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class AqiCalendarVO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * @Description: aqi
     */
	private Integer dayNum;
	
	/**
     * @Description: aqi
     */
	private String AQI;
	
	/**
     * @Description: 主要污染物
     */
	private String mainPollutant;
	
	/**
     * @Description: aqi
     */
	private List<CalendarFactor> pollutant;
	
}
