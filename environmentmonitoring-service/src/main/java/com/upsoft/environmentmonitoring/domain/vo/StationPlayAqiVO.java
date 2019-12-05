package com.upsoft.environmentmonitoring.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName StationPlayAqiVO
 * @Description 监测地图24小时播放数据返回实体
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class StationPlayAqiVO {

	/**
     * @Description: time
     */
	private String time;
	
	/**
     * @Description: value
     */
	private List<StationPlayAqi> value;
	
}
