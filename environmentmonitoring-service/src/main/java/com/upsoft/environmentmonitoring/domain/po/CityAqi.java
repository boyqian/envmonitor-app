package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName CityAqi
 * @Description 城市AQI和6项因子的小时监测数据和天监测数据
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class CityAqi implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Description: 城市AQI主键ID
     */
    private String cityAqiId;

    /**
     * @Description: 监测站点所属城市名称
     */
    private String cityName;

    /**
     * @Description: 城市代码
     */
    private String cityCode;

    /**
     * @Description: 城市地理坐标经度(四川省的是WGS84-大地坐标系)
     */
    private String cityLongitude;

    /**
     * @Description: 城市地理坐标纬度(四川省的是WGS84-大地坐标系)
     */
    private String cityLatitude;

    /**
     * @Description: 数据发布时间
     */
    private LocalDateTime publishDateTime;

    /**
     * @Description: 发布AQI数据类别: H-小时数据, D-天数据
     */
    private String cityAqiType;

    /**
     * @Description: 空气质量指数(AQI), 即air quality index, 是定量描述空气质量状况的无纲量指数
     */
    private Integer aqi;

    /**
     * @Description: 空气质量指数等级, 1-优; 2-良; 3-轻度污染; 4-中度污染; 5-重度污染; 6-严重污染
     */
    private Integer aqiLevel;

    /**
     * @Description: 首要污染物
     */
    private String primaryPollutant;

    /**
     * @Description: 城市PM2.5颗粒物（粒径小于等于2.5μm）1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer pm25;

    /**
     * @Description: 城市PM10颗粒物（粒径小于等于10μm）1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer pm10;

    /**
     * @Description: 城市二氧化硫1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer so2;

    /**
     * @Description: 城市二氧化氮1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer no2;

    /**
     * @Description: 城市一氧化碳1小时或1天内平均浓度, 单位:mg/m3
     */
    private Float co;

    /**
     * @Description: 城市臭氧1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer o3;

    /**
     * @Description: 城市PM2.5分指数IAQI
     */
    private Integer iaqiPm25;

    /**
     * @Description: 城市PM10分指数IAQI
     */
    private Integer iaqiPm10;

    /**
     * @Description: 城市二氧化硫分指数IAQI
     */
    private Integer iaqiSo2;

    /**
     * @Description: 城市二氧化氮分指数IAQI
     */
    private Integer iaqiNo2;

    /**
     * @Description: 城市一氧化碳分指数IAQI
     */
    private Integer iaqiCo;

    /**
     * @Description: 城市臭氧分指数IAQI
     */
    private Integer iaqiO3;

    /**
     * @Description: 创建人
     */
    private String creator;

    /**
     * @Description: 创建时间
     */
    private LocalDateTime createTime;

    /**
     * @Description: 修改人
     */
    private String editor;

    /**
     * @Description: 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * @Description: 删除标识(0-未删除, 1-已删除)
     */
    private Integer isDeleted;

    /**
     * @Description: 备用字段1:前端传年月
     */
    private String standby1;

    /**
     * @Description: 备用字段2
     */
    private String standby2;

    /**
     * @Description: 备用字段3
     */
    private String standby3;

    /**
     * @Description: 备用字段4
     */
    private String standby4;

    /**
     * @Description: 备用字段5
     */
    private String standby5;


}
