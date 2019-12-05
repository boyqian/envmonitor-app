package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName CityAqi
 * @Description 微站点的各个因子的数据
 * @Author Wang wanqian
 * @Date 2019-11-18
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class AqiReportHour implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * @Description:空气质量指数id
     */
	private String hourAqiId;
	
	/**
     * @Description:监测日期
     */
	private LocalDateTime monitorDate;
	
	/**
     * @Description:aqi
     */
	private Integer aqiValue;
	
	/**
     * @Description:AQI级别编码
     */
	private Integer aqiLevelCode;
	
	/**
     * @Description:AQI级别名称
     */
	private String aqiLevelName;
	
	/**
     * @Description:pm25级别编码
     */
	private Integer pm25LevelCode;
	
	/**
     * @Description:pm25级别名称
     */
	private String pm25LevelName;
	
	/**
     * @Description:pm10级别编码
     */
	private Integer pm10LevelCode;
	
	/**
     * @Description:pm10级别名称
     */
	private String pm10LevelName;

	/**
     * @Description:SO2 1小时平均值
     */
	private Integer so2;
	
	/**
     * @Description:SO2 1小时平均分指数
     */
	private Integer so2Aqi;
	
	/**
     * @Description:NO2 1小时平均值
     */
	private Integer no2;
	
	/**
     * @Description:NO2 1小时平均分指数
     */
	private Integer no2Aqi;
	
	/**
     * @Description:CO 1小时平均值
     */
	private Float co;
	
	/**
     * @Description:CO 1小时平均分指数
     */
	private Integer coAqi;
	
	/**
     * @Description:PM10 1小时平均值
     */
	private Integer pm10;
	
	/**
     * @Description:PM10 24小时滑动平均值
     */
	private Integer pm10Slide;
	
	/**
     * @Description:PM10 24小时滑动平均分指数
     */
	private Integer pm10SlideAqi;
	
	/**
     * @Description:PM2.5 1小时平均值
     */
	private Integer pm25;
	
	/**
     * @Description:PM25 24小时滑动平均值
     */
	private Integer pm25Slide;
	
	/**
     * @Description:PM25 24小时滑动平均分指数
     */
	private Integer pm25SlideAqi;
	
	/**
     * @Description:O3 1小时平均最大值
     */
	private Integer o3;
	
	/**
     * @Description:O3 1小时平均最大值分指数
     */
	private Integer o3Aqi;
	
	/**
     * @Description:创建时间
     */
	private LocalDateTime createTime; 
	
	/**
     * @Description:状态,1：有效、0：无效、-1；删除
     */
	private String status;
	
	/**
     * @Description:时间戳
     */
	private String versionTime;
	
	/**
     * @Description:监管点id
     */
	private String pointId;
	
	/**
     * @Description:是否达标 1达标，0不达标
     */
	private String isGood;
	
	/**
     * @Description:风速
     */
	private String windSpeed;
	
	/**
     * @Description:风向1000北风 100北东北1002东北1003东东北1004东1005东东南1006东南 1007南东南1008南1009南西南1010西南1011西西南1012西1013西西北1014西北1015北西北                                       
     */
	private String windDirection;
	
	/**
     * @Description:温度
     */
	private String temperature;
	
	/**
     * @Description:湿度
     */
	private String humidity;
	
	/**
     * @Description:大气压  
     */
	private String atmosphericPressure;
	
	/**
     * @Description:SO2 是否达标达标1未达标 0
     */
	private String so2Standard;
	
	/**
     * @Description:NO2 是否达标达标1未达标 0
     */
	private String no2Standard;
	
	/**
     * @Description:CO  是否达标达标1未达标 0
     */
	private String coStandard;
	
	/**
     * @Description:PM10 是否达标达标1未达标 0
     */
	private String pm10Standard;
	
	/**
     * @Description:PM2.5 是否达标达标1未达标 0
     */
	private String pm25Standard;
	
	/**
     * @Description:O3 是否达标 达标1  未达标 0
     */
	private String o3Standard;
	
	/**
     * @Description:监测点名称
     */
	private String pointName;
	
	/**
     * @Description:省份
     */
	private String xzsf;
	
	/**
     * @Description:省份代码
     */
	private String xzsfCode;
	
	/**
     * @Description:市区
     */
	private String xzsq;
	
	/**
     * @Description:市区代码
     */
	private String xzsqCode;
	
	/**
     * @Description:县
     */
	private String xzx;
	
	/**
     * @Description:县代码
     */
	private String xzxCode;
	
	/**
     * @Description:街道
     */
	private String xzjd;
	
	/**
     * @Description:街道代码
     */
	private String xzjdCode;
	
	/**
     * @Description:区域
     */
	private String xzc;
	
	/**
     * @Description:区域代码
     */
	private String xzcCode;
	
	/**
     * @Description:经度
     */
	private String longitude;
	
	/**
     * @Description:纬度
     */
	private String latitude;
	
	/**
     * @Description:SO2小时最大值
     */
	private Integer so2Max;
	
	/**
     * @Description:NO2小时最大值
     */
	private Integer no2Max;
	
	/**
     * @Description:CO小时最大值
     */
	private Integer coMax;
	
	/**
     * @Description:PM10小时最小值
     */
	private Integer pm10Min;
	
	/**
     * @Description:PM10小时最大值
     */
	private Integer pm10Max;
	
	/**
     * @Description:PM25小时最小值
     */
	private Integer pm25Min;
	
	/**
     * @Description:PM25小时最大值
     */
	private Integer pm25Max;
	
	/**
     * @Description:O3小时最大值
     */
	private Integer O3Max;
	
	/**
     * @Description:AQI小时最大值
     */
	private Integer aqiMax;
	
	/**
     * @Description: 备用字段1:前端传参单个站点的站点id,对应pointId
     */
	private String standby1;
	
    /**
     * @Description: 备用字段2:前端传参单个站点折线图查询开始时间
     */
    private String standby2;

    /**
     * @Description: 备用字段3:前端传参单个站点折线图查询结束时间
     */
    private String standby3;
    
    /**
     * @Description: 备用字段4:微站点类型
     */
    private String standby4;
}
