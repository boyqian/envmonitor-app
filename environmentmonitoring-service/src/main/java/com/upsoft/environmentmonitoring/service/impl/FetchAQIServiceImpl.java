package com.upsoft.environmentmonitoring.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.upsoft.environmentmonitoring.dao.CityAqiMapper;
import com.upsoft.environmentmonitoring.dao.StationAqiMapper;
import com.upsoft.environmentmonitoring.domain.po.CityAqi;
import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.service.FetchAQIService;
import com.upsoft.environmentmonitoring.utils.DateUtil;
import com.upsoft.environmentmonitoring.utils.ObjectIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FetchAQIServiceImpl
 * @Description 获取城市AQI天数据实现类
 * @Author Administrator
 * @Date 2019/10/29
 * @Version 1.0
 */
@Service
@DS("environmentmonitoring")
//@EnableScheduling
public class FetchAQIServiceImpl implements FetchAQIService {

	/**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入RestTemplate
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * @Description: 注入CityAqiMapper
	 */
	@Autowired
	private CityAqiMapper cityAqiMapper;

	/**
	 * @Description: 注入StationAqiMapper
	 */
	@Autowired
	private StationAqiMapper stationAqiMapper;

	/**
	 * @Description: 从application.yml中读取四川省AQI数据抓取url
	 */
	@Value("${resttemplate.url.sichuan_aqi}")
	private String urlSichuanAqi;

	/**
	 * @Description: 从application.yml中读取四川省AQI数据抓取的城市代码
	 */
	@Value("${resttemplate.url.sichuan_aqi_city_code}")
	private String sichuanAqiCityCode;

	/**
	 * @Description: 从application.yml中读取四川省AQI数据抓取的城市小时数据代码
	 */
	@Value("${resttemplate.url.sichuan_city_hour_aqi_type}")
	private String sichuanCityHourAqiType;

	/**
	 * @Description: 从application.yml中读取四川省AQI数据抓取的监测站点小时数据代码
	 */
	@Value("${resttemplate.url.sichuan_station_hour_aqi_type}")
	private String sichuanStationHourAqiType;

	/**
	 * @Description: 从application.yml中读取四川省AQI数据抓取的城市天数据代码
	 */
	@Value("${resttemplate.url.sichuan_city_day_aqi_type}")
	private String sichuanCityDayAqiType;

	/**
	 * @Description: 从application.yml中读取四川省AQI数据抓取的监测站点天数据代码
	 */
	@Value("${resttemplate.url.sichuan_station_day_aqi_type}")
	private String sichuanStationDayAqiType;

	/**
	 * @Author weiwei
	 * @Description 新增四川省城市和站点的AQI天数据
	 * @Date 10:45 2019/10/29
	 * @Params []
	 * @return java.lang.String
	 **/
	//@Scheduled(cron = "0 30 * * * ?")
	//@Async
	@Transactional
	@Override
	public void insertSichuanDayAQI() {
		logger.info("----------四川省天数据抓取开始-----------");
		String cityDayAqi = restTemplate.getForObject(urlSichuanAqi+"?"+"type="+sichuanCityDayAqiType+"&citycode="+sichuanAqiCityCode, String.class);
		List<CityAqi> cityAqiList = jsonToObjectForCityAqi(cityDayAqi);
		LocalDateTime latestCityPublishTime = cityAqiList.get(0).getPublishDateTime();
		LocalDateTime dataBaseLatestCityTime = cityAqiMapper.getLatestTimeForCityAqi("D");
		logger.info("四川省城市天数据最后更新时间为:[{}],当前抓取时间为:[{}]", dataBaseLatestCityTime, latestCityPublishTime);
		if(dataBaseLatestCityTime == null || ChronoUnit.DAYS.between(dataBaseLatestCityTime, latestCityPublishTime) > 0) {
			logger.info("#########四川省城市天数据更新开始##########");
			cityAqiMapper.insertCityAqiBatch(cityAqiList);
			logger.info("#########四川省城市天数据更新结束##########");
		}
		LocalDateTime dataBaseLatestStationTime = stationAqiMapper.getLatestTimeForStationAqi("SD");
		for(CityAqi cityAqi: cityAqiList){
			String stationDayAqi = restTemplate.getForObject(urlSichuanAqi+"?"+"type="+sichuanStationDayAqiType+"&citycode="+cityAqi.getCityCode()+"00000000", String.class);
			List<StationAqi> stationDayAqiList = jsonToObjectForStationAqi(stationDayAqi);
			LocalDateTime latestStationPublishTime = stationDayAqiList.get(0).getPublishDateTime();
			if(dataBaseLatestStationTime == null || ChronoUnit.DAYS.between(dataBaseLatestStationTime, latestStationPublishTime) > 0) {
				logger.info("#########四川省监测站点天数据更新开始##########");
				stationAqiMapper.insertStationAqiBatch(stationDayAqiList);
				logger.info("#########四川省监测站点天数据更新结束##########");
			}
		}
	}

	/**
	 * @Author weiwei
	 * @Description 新增四川省城市和站点的AQI小时数据
	 * @Date 10:45 2019/10/29
	 * @Params []
	 * @return java.lang.String
	 **/
	//@Scheduled(cron = "0 50 * * * ?")
	//@Async
	@Transactional
	@Override
	public void insertSichuanHourAQI() {
		logger.info("----------四川省小时数据抓取开始-----------");
		String cityHourAqi = restTemplate.getForObject(urlSichuanAqi+"?"+"type=H&citycode="+sichuanAqiCityCode, String.class);
		List<CityAqi> cityAqiList = jsonToObjectForCityAqi(cityHourAqi);
		LocalDateTime latestCityPublishTime = cityAqiList.get(0).getPublishDateTime();
		LocalDateTime dataBaseLatestCityTime = cityAqiMapper.getLatestTimeForCityAqi("H");
		logger.info("四川省小时数据最后更新时间为:[{}],当前抓取时间为:[{}]", dataBaseLatestCityTime, latestCityPublishTime);
		// 如果为null则表示是系统首次启动
		if(dataBaseLatestCityTime == null || ChronoUnit.HOURS.between(dataBaseLatestCityTime, latestCityPublishTime) > 0) {
			logger.info("#########四川省城市小时数据更新开始##########");
			cityAqiMapper.insertCityAqiBatch(cityAqiList);
			logger.info("#########四川省城市小时数据更新结束##########");
		}
		LocalDateTime dataBaseLatestStationTime = stationAqiMapper.getLatestTimeForStationAqi("SH");
		for(CityAqi cityAqi: cityAqiList){
			String stationHourAqi = restTemplate.getForObject(urlSichuanAqi+"?"+"type=SH&citycode="+cityAqi.getCityCode()+"00000000", String.class);
			List<StationAqi> stationHourAqiList = jsonToObjectForStationAqi(stationHourAqi);
			LocalDateTime latestStationPublishTime = stationHourAqiList.get(0).getPublishDateTime();
			if(dataBaseLatestStationTime == null || ChronoUnit.HOURS.between(dataBaseLatestStationTime, latestStationPublishTime) > 0) {
				logger.info("#########四川省监测站点小时数据更新开始##########");
				stationAqiMapper.insertStationAqiBatch(stationHourAqiList);
				logger.info("#########四川省监测站点小时数据更新结束##########");
			}
		}
	}

	/**
	 * @Author weiwei
	 * @Description 将获取的四川省城市AQI数据JSON字符串转换为CityAqi集合对象
	 * @Date 14:09 2019/11/1
	 * @Params [jsonString]
	 * @return java.util.List<java.lang.String>
	 **/
	private List<CityAqi> jsonToObjectForCityAqi(String jsonString) {
		List<CityAqi> cityAqiList = new ArrayList<>();
		JSONArray jsonArray = JSONObject.parseArray(jsonString);
		for(int i = 0; i < jsonArray.size(); i++){
			JSONObject cityAqiJson = jsonArray.getJSONObject(i);
			// 城市名称
			String cityName = cityAqiJson.getString("CITYNAME");
			// 城市编码
			String cityCode = cityAqiJson.getString("CITYCODE");
			// 城市地理坐标经度(四川省的是WGS84-大地坐标系)
			String cityLongitude = cityAqiJson.getString("CITYLNG");
			// 城市地理坐标纬度(四川省的是WGS84-大地坐标系)
			String cityLatitude = cityAqiJson.getString("CITYLAT");
			// 发布时间
			String publishDateTime = cityAqiJson.getString("PUBDATE");
			// 发布AQI类型: H-小时数据, D-天数据
			String cityAqiType = cityAqiJson.getString("PUBTYPE");
			// 空气质量指数(AQI), 即air quality index, 是定量描述空气质量状况的无纲量指数
			String aqi = cityAqiJson.getString("AQI");
			// 空气质量指数等级, 1-优; 2-良; 3-轻度污染; 4-中度污染; 5-重度污染; 6-严重污染
			String aqiLevel = cityAqiJson.getString("AQILV");
			// 首要污染物
			String primaryPollutant = cityAqiJson.getString("PRIMARYPOLLUTANT");
			if("—".equals(primaryPollutant)){
				primaryPollutant = "";
			}
			// 城市PM2.5颗粒物（粒径小于等于2.5μm）1小时平均浓度, 单位:ug/m3
			String pm2_5 = cityAqiJson.getString("PM2_5");
			// 城市PM2.5分指数IAQI
			String ipm2_5 = cityAqiJson.getString("IPM2_5");
			// 城市PM10颗粒物（粒径小于等于10μm）1小时平均浓度, 单位:ug/m3
			String pm10 = cityAqiJson.getString("PM10");
			// 城市PM10分指数IAQI
			String ipm10 = cityAqiJson.getString("IPM10");
			// 城市二氧化硫1小时平均浓度, 单位:ug/m3
			String so2 = cityAqiJson.getString("SO2");
			// 城市二氧化硫分指数IAQI
			String iso2 = cityAqiJson.getString("ISO2");
			// 城市二氧化氮1小时平均浓度, 单位:ug/m3
			String no2 = cityAqiJson.getString("NO2");
			// 城市二氧化氮分指数IAQI
			String ino2 = cityAqiJson.getString("INO2");
			// 城市一氧化碳1小时平均浓度, 单位:mg/m3
			String co = cityAqiJson.getString("CO");
			// 城市一氧化碳分指数IAQI
			String ico = cityAqiJson.getString("ICO");
			// 城市臭氧1小时平均浓度, 单位:ug/m3
			String o3 = cityAqiJson.getString("O3");
			// 城市臭氧分指数IAQI
			String io3 = cityAqiJson.getString("O3");
			// 构造封装CityAqi
			CityAqi cityAqi = new CityAqi()
					.setCityAqiId(ObjectIdUtil.id())
					.setCityName(cityName)
					.setCityCode(cityCode)
					.setCityLongitude(cityLongitude)
					.setCityLatitude(cityLatitude)
					.setPublishDateTime(DateUtil.stringToLocalDateTime(publishDateTime,"yyyy-MM-dd HH:mm:ss"))
					.setCityAqiType(cityAqiType)
					.setAqi(Integer.parseInt(aqi))
					.setAqiLevel(Integer.parseInt(aqiLevel))
					.setPrimaryPollutant(primaryPollutant)
					.setPm25(Integer.parseInt(pm2_5))
					.setPm10(Integer.parseInt(pm10))
					.setSo2(Integer.parseInt(so2))
					.setNo2(Integer.parseInt(no2))
					.setCo(Float.parseFloat(co))
					.setO3(Integer.parseInt(o3))
					.setIaqiPm25(Integer.parseInt(ipm2_5))
					.setIaqiPm10(Integer.parseInt(ipm10))
					.setIaqiSo2(Integer.parseInt(iso2))
					.setIaqiNo2(Integer.parseInt(ino2))
					.setIaqiCo(Integer.parseInt(ico))
					.setIaqiO3(Integer.parseInt(io3))
					.setCreateTime(LocalDateTime.now());
			// 根据配置的要抓取的城市code获取对应城市的数据
			if(sichuanAqiCityCode.equals(cityAqi.getCityCode())) {
				cityAqiList.add(cityAqi);
			}
		}
		return cityAqiList;
	}

	/**
	 * @Author weiwei
	 * @Description 将获取的四川省监测站点AQI数据JSON字符串转换为StationAqi集合对象
	 * @Date 14:09 2019/11/1
	 * @Params [jsonString]
	 * @return java.util.List<java.lang.String>
	 **/
	private List<StationAqi> jsonToObjectForStationAqi(String jsonString) {
		List<StationAqi> stationAqiList = new ArrayList<>();
		JSONArray jsonArray = JSONObject.parseArray(jsonString);
		for(int i = 0; i < jsonArray.size(); i++){
			JSONObject stationHourAqi = jsonArray.getJSONObject(i);
			// 城市名称
			String cityName = stationHourAqi.getString("CITYNAME");
			// 城市编码
			String cityCode = stationHourAqi.getString("CITYCODE");
			// 监测站点名称
			String stationName = stationHourAqi.getString("STATIONNAME");
			// 监测站点编码
			String stationCode = stationHourAqi.getString("STATIONCODE");
			// 监测站点所属行政区县名称
			String countyName = stationHourAqi.getString("COUNTYNAME");
			// 监测站点坐标经度(四川省的是WGS84-大地坐标系)
			String stationLongitude = stationHourAqi.getString("LNG");
			// 监测站点坐标纬度(四川省的是WGS84-大地坐标系)
			String stationLatitude = stationHourAqi.getString("LAT");
			// 发布时间
			String publishDateTime = stationHourAqi.getString("PUBDATE");
			// 发布AQI类型: SH-小时数据, SD-天数据
			String stationAqiType = stationHourAqi.getString("PUBTYPE");
			// 空气质量指数(AQI), 即air quality index, 是定量描述空气质量状况的无纲量指数
			String aqi = stationHourAqi.getString("AQI");
			// 空气质量指数等级, 1-优; 2-良; 3-轻度污染; 4-中度污染; 5-重度污染; 6-严重污染
			String aqiLevel = stationHourAqi.getString("AQILV");
			// 首要污染物
			String primaryPollutant = stationHourAqi.getString("PRIMARYPOLLUTANT");
			if("—".equals(primaryPollutant)){
				primaryPollutant = "";
			}
			// 城市PM2.5颗粒物（粒径小于等于2.5μm）1小时平均浓度, 单位:ug/m3
			String pm2_5 = stationHourAqi.getString("PM2_5");
			// 城市PM10颗粒物（粒径小于等于10μm）1小时平均浓度, 单位:ug/m3
			String pm10 = stationHourAqi.getString("PM10");
			// 城市二氧化硫1小时平均浓度, 单位:ug/m3
			String so2 = stationHourAqi.getString("SO2");
			// 城市二氧化氮1小时平均浓度, 单位:ug/m3
			String no2 = stationHourAqi.getString("NO2");
			// 城市一氧化碳1小时平均浓度, 单位:mg/m3
			String co = stationHourAqi.getString("CO");
			// 城市臭氧1小时平均浓度, 单位:ug/m3
			String o3 = stationHourAqi.getString("O3");
			// 构造封装StationAqi
			StationAqi stationAqi = new StationAqi()
					.setStationAqiId(ObjectIdUtil.id())
					.setCityName(cityName)
					.setCityCode(cityCode)
					.setStationName(stationName)
					.setStationCode(stationCode)
					.setCountyName(countyName)
					.setStationLongitude(stationLongitude)
					.setStationLatitude(stationLatitude)
					.setPublishDateTime(DateUtil.stringToLocalDateTime(publishDateTime,"yyyy-MM-dd HH:mm:ss"))
					.setStationAqiType(stationAqiType)
					.setAqi(Integer.parseInt(aqi))
					.setAqiLevel(Integer.parseInt(aqiLevel))
					.setPrimaryPollutant(primaryPollutant)
					.setPm25(Integer.parseInt(pm2_5))
					.setPm10(Integer.parseInt(pm10))
					.setSo2(Integer.parseInt(so2))
					.setNo2(Integer.parseInt(no2))
					.setCo(Float.parseFloat(co))
					.setO3(Integer.parseInt(o3))
					.setCreateTime(LocalDateTime.now());
			// 根据配置的要抓取的城市code获取对应的城市站点数据
			if(sichuanAqiCityCode.equals(stationAqi.getCityCode())) {
				stationAqiList.add(stationAqi);
			}
		}
		return stationAqiList;
	}

}
