package com.upsoft.environmentmonitoring.service;

import java.util.List;

import com.upsoft.environmentmonitoring.domain.po.CityAqi;
import com.upsoft.environmentmonitoring.domain.vo.CityAqiVO;

/**
 * @ClassName CityAqiHourDayService
 * @Description 首页24小时30天返回数据格式
 * @Author Wang wanqian
 * @Date 2019-12-03
 * @Version 1.0
 */
public interface CityAqiHourDayService {

	/**
	 * @Author Wang wanqian
	 * @Description 适配器接口，使得返回内容改变
	 * @Date 2019-12-03
	 * @Params []
	 * @return 
	 **/
	List<CityAqiVO> getCityAqiHourDay(CityAqi cityAqi);
}
