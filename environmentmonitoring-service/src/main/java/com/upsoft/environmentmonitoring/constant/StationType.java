package com.upsoft.environmentmonitoring.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName StationType
 * @Description 站点类型枚举
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum StationType {

	STATE_CONTROL("国控","SC"),
	PROVINCIAL_CONTROL("省控","PC"),
	CITY_CONTROL("市控","CC"),
	MICRO_STATION("微站","MS");
	
	/**
     * @Description: 名称
     */
	private String stationName;
	
	/**
     * @Description: 代码
     */
	private String stationType;
	
}
