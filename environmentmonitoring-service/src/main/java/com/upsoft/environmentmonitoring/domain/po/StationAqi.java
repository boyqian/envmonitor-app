package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName StationAqi
 * @Description 监测站点AQI和6项因子的小时监测数据和天监测数据
 * @Author Wei Wei
 * @Date 2019-11-01
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class StationAqi implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Description: 监测站点AQI主键ID
     */
    private String stationAqiId;

    /**
     * @Description: 监测站点名称
     */
    private String stationName;

    /**
     * @Description: 监测站点代码
     */
    private String stationCode;

    /**
     * @Description: 监测站点类别( SC(State Control)-国控站点, PC(Provincial Control)-省控站点, CC(City Control)-市控站点, MS(Micro Station)-微站 )
     */
    private String stationType;

    /**
     * @Description: 监测站点所属行政区县名称
     */
    private String countyName;

    /**
     * @Description: 监测站点所属城市名称
     */
    private String cityName;

    /**
     * @Description: 监测站点所属城市代码
     */
    private String cityCode;

    /**
     * @Description: 监测站点坐标经度
     */
    private String stationLongitude;

    /**
     * @Description: 监测站点坐标纬度
     */
    private String stationLatitude;

    /**
     * @Description: 数据发布时间
     */
    private LocalDateTime publishDateTime;

    /**
     * @Description: 发布AQI数据类别
     */
    private String stationAqiType;

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
     * @Description: 监测站点PM2.5颗粒物（粒径小于等于2.5μm）1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer pm25;

    /**
     * @Description: 监测站点PM10颗粒物（粒径小于等于10μm）1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer pm10;

    /**
     * @Description: 监测站点二氧化硫1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer so2;

    /**
     * @Description: 监测站点二氧化氮1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer no2;

    /**
     * @Description: 监测站点一氧化碳1小时或1天内平均浓度, 单位:mg/m3
     */
    private Float co;

    /**
     * @Description: 监测站点臭氧1小时或1天内平均浓度, 单位:ug/m3
     */
    private Integer o3;

    /**
     * @Description: 监测站点PM2.5分指数IAQI
     */
    private Integer iaqiPm25;

    /**
     * @Description: 监测站点PM10分指数IAQI
     */
    private Integer iaqiPm10;

    /**
     * @Description: 监测站点二氧化硫分指数IAQI
     */
    private Integer iaqiSo2;

    /**
     * @Description: 监测站点二氧化氮分指数IAQI
     */
    private Integer iaqiNo2;

    /**
     * @Description: 监测站点一氧化碳分指数IAQI
     */
    private Integer iaqiCo;

    /**
     * @Description: 监测站点臭氧分指数IAQI
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
     * @Description: 备用字段1:前端传参"H"表示监测地图展示近1h的站点数据
     * 						"D"表示监测地图展示近24小时动画播放
     */
    private String standby1;

    /**
     * @Description: 备用字段2:前端传参单个站点折线图查询开始时间/ 前端传时间点
     */
    private String standby2;

    /**
     * @Description: 备用字段3:前端传参单个站点折线图查询结束时间
     */
    private String standby3;

    /**
     * @Description: 备用字段4:循环遍历station_name
     */
    private String standby4;

    /**
     * @Description: 备用字段5:站点对比用到同比环比数据时间
     */
    private String standby5;


}
