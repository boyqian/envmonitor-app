package com.upsoft.environmentmonitoring.service;

import java.util.List;

import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.StationAqiVO;

/**
 * @ClassName StationVideoService
 * @Description  StationVideoService 适配器 接口
 * @Author Wang wanqian
 * @Date 2019-11-29
 * @Version 1.0
 */
public interface StationVideoService {

	/**
	 * @Author Wang wanqian
	 * @Description 适配器接口，使得返回内容改变
	 * @Date 2019-11-22
	 * @Params [stationAqi]
	 * @return List<StationAqiVO>
	 **/
	List<StationAqiVO> getStationVideo(StationAqi stationAqi);
}
