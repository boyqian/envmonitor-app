package com.upsoft.environmentmonitoring.service;

import java.util.List;

import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.StationPlayAqiVO;

/**
 * @ClassName StationPlayAqiService
 * @Description  StationPlayAqiService 适配器 接口
 * @Author Wang wanqian
 * @Date 2019-11-22
 * @Version 1.0
 */
public interface StationPlayAqiService {

	/**
	 * @Author Wang wanqian
	 * @Description 适配器接口，使得传参内容改变返回内容改变
	 * @Date 2019-11-22
	 * @Params [stationAqi]
	 * @return List<StationPlayAqiVO>
	 **/
	List<StationPlayAqiVO> request(StationAqi stationAqi);
}
