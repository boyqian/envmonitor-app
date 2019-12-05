package com.upsoft.environmentmonitoring.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName HoutDayType
 * @Description 小时数据天数据枚举
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum HourDayType {
	
	H("本年小时数据","H"), // 本年小时数据
	D("本年天数据","D"), // 本年天数据
	LH("去年小时数据","LH"), // 去年小时数据
	LD("去年天数据","LD"); // 去年天数据
	
	/**
     * @Description: 描述
     */
	private String desc;
	
	/**
     * @Description: 值
     */
	private String value;

}