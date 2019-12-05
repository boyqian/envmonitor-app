package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName TargetDays
 * @Description 返回首页目标天数与达标天数信息
 * @Author Wang wanqian
 * @Date 2019-11-12
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class TargetDays implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
     * @Description: 达标天数
     */
	private String dDays;
}
