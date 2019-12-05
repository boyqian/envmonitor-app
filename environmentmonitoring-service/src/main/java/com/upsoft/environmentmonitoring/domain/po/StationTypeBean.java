package com.upsoft.environmentmonitoring.domain.po;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;


/**
 * @ClassName StationTypeBean
 * @Description 自定义配置站点类型和站点名称的映射关系
 * @Author Wang wanqian
 * @Date 2019/11/22 09:37
 * @Version 1.0
 */
@Configuration
@PropertySource("classpath:stationtype.properties")
@ConfigurationProperties(prefix = "stationtype")
@Data
public class StationTypeBean {

	/**
	 * @Description: "国控"
	 */
	private String SC;
	
	/**
	 * @Description: "省控"
	 */
	private String PC;
	
	/**
	 * @Description: "市控"
	 */
	private String CC;
	
	/**
	 * @Description: "微站"
	 */
	private String MS;
}
