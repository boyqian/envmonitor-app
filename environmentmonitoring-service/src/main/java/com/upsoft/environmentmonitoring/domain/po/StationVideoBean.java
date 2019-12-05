package com.upsoft.environmentmonitoring.domain.po;

import java.util.Map;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * @ClassName StationVideoBean
 * @Description 自定义配置站点和视频的映射关系
 * @Author Wang wanqian
 * @Date 2019/11/29 17:37
 * @Version 1.0
 */
@Configuration
@PropertySource("classpath:stationvideo.properties")
@ConfigurationProperties(prefix = "stationvideo")
@Data
public class StationVideoBean {

	private Map<String,String> maps;
}
