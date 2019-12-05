package com.upsoft.environmentmonitoring.domain.vo;


import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @ClassName GoodDays
 * @Description 首页-本年或本月优良天数数据实体返回
 * @Author Wang wanqian
 * @Date 2019-11-15
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class GoodDays implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
     * @Description: 优天数
     */
    private Integer excellent;
    
    /**
     * @Description: 良天数
     */
    private Integer good;
    
    /**
     * @Description: 轻度污染天数
     */
    private Integer light;
    
    /**
     * @Description: 中度污染天数
     */
    private Integer moderate;
    
    /**
     * @Description: 重度污染天数
     */
    private Integer severe;
    
    /**
     * @Description: 严重污染天数
     */
    private Integer serious;
    
    /**
     * @Description: 优天数-同比对比本年或本月数据增加或减少多少天
     */
    private Integer excellentDays;
    
    /**
     * @Description: 良天数-同比对比本年或本月数据增加或减少多少天
     */
    private Integer goodDays;
    
    /**
     * @Description: 轻度天数-同比对比本年或本月数据增加或减少多少天
     */
    private Integer lightDays;
    
    /**
     * @Description: 中度天数-同比对比本年或本月数据增加或减少多少天
     */
    private Integer moderateDays;
    
    /**
     * @Description: 重度天数-同比对比本年或本月数据增加或减少多少天
     */
    private Integer severeDays;
    
    /**
     * @Description: 严重天数-同比对比本年或本月数据增加或减少多少天
     */
    private Integer seriousDays;
}
