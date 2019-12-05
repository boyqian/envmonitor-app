package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName StationVO
 * @Description 获取返回所有站点名称和code
 * @Author Wang wanqian
 * @Date 2019-11-29
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class StationVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
     * @Description: 站点Code
     */
	private String stationCode;
	
	/**
     * @Description: 站点名称
     */
	private String stationName;
}
