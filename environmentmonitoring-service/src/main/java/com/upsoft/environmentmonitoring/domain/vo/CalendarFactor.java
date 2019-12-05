package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName CalendarFactor
 * @Description Aqi日历前端返回因子实体类
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class CalendarFactor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 /**
     * @Description: type
     */
	private String type;
	
	 /**
     * @Description: value
     */
	private String value;
}
