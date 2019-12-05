package com.upsoft.environmentmonitoring.service;

/**
 * @ClassName FetchAQIService
 * @Description 抓取AQI数据
 * @Author Administrator
 * @Date 2019/10/29
 * @Version 1.0
 */
public interface FetchAQIService {

	/**
	 * @Author weiwei
	 * @Description 新增四川省城市和站点的AQI天数据
	 * @Date 10:45 2019/10/29
	 * @Params []
	 * @return 
	 **/
	void insertSichuanDayAQI();

	/**
	 * @Author weiwei
	 * @Description 新增四川省城市和站点的AQI小时数据
	 * @Date 10:45 2019/10/29
	 * @Params []
	 * @return 
	 **/
	void insertSichuanHourAQI();

}
