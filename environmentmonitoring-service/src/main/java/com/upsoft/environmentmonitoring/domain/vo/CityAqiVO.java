package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName CityAqiVO
 * @Description 首页-24小时30天返回数据格式
 * @Author Wang wanqian
 * @Date 2019-12-03
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class CityAqiVO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * @Description: 因子
     */
	private Factor factor;
    
    /**
     * @Description: 数据发布时间
     */
    private String publishDateTime;
}
