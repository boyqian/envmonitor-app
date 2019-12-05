package com.upsoft.environmentmonitoring.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName StationVideo
 * @Description 视频站点关联类，解决properties不支持中文key的问题
 * @Author Wang wanqian
 * @Date 2019-12-02
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum StationVideo {

	STATION_JINKE("A","金科集美嘉悦"),
	STATION_MINJIE("B","敏捷锦绣源著"),
	STATION_BANGTAI("C","邦泰河东院子");
	
	/**
     * @Description: 名称点位
     */
	private String stationName;
	
	/**
     * @Description: 视频名称
     */
	private String videoName;
	
}