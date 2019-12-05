package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Problem
 * @Description 问题表（报警）
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Description: 问题主键
     */
    private String problemId;

    /**
     * @Description: 监测点id
     */
    private String pointId;

    /**
     * @Description: 监测类型编码(1000-空气质量, 1001-VOC)
     */
    private String monitorTypeCode;

    /**
     * @Description: 监测类型名称
     */
    private String monitorTypeName;

    /**
     * @Description: 监测点名称
     */
    private String pointName;

    /**
     * @Description: 监管点所监测污染因子(aqi,so2,no2,co,pm10,pm2.5,o3,vocs)
     */
    private String pollFactor;

    /**
     * @Description: 问题等级编码(1000:一级, 1001:二级, 1002:三级)
     */
    private String problemLevelCode;

    /**
     * @Description: 问题等级名称
     */
    private String problemLevelName;

    /**
     * @Description: 问题详情
     */
    private String problemDetail;

    /**
     * @Description: 问题类型编码(1000-监测报警, 1001-预测报警)
     */
    private String problemTypeCode;

    /**
     * @Description: 问题类型名称
     */
    private String problemTypeName;

    /**
     * @Description: 问题状态编码(1000-未读, 1001-已读)
     */
    private String problemStatusCode;

    /**
     * @Description: 问题状态名称
     */
    private String problemStatusName;

    /**
     * @Description: 行政省份名称
     */
    private String xzsf;

    /**
     * @Description: 行政省份区划编码
     */
    private String xzsfCode;

    /**
     * @Description: 行政市区名称
     */
    private String xzsq;

    /**
     * @Description: 行政市区区划编码
     */
    private String xzsqCode;

    /**
     * @Description: 行政县名称
     */
    private String xzx;

    /**
     * @Description: 行政县区划编码
     */
    private String xzxCode;

    /**
     * @Description: 行政街道名称
     */
    private String xzjd;

    /**
     * @Description: 行政街道区划编码
     */
    private String xzjdCode;

    /**
     * @Description: 行政村（社区）名称
     */
    private String xzc;

    /**
     * @Description: 行政村（社区）区划编码
     */
    private String xzcCode;

    /**
     * @Description: 地址
     */
    private String address;

    /**
     * @Description: 经度
     */
    private String longitude;

    /**
     * @Description: 纬度
     */
    private String latitude;

    /**
     * @Description: 状态,1：有效、0：无效、-1；删除
     */
    private String status;

    /**
     * @Description: 时间戳
     */
    private BigDecimal versionTime;

    /**
     * @Description: 创建时间
     */
    private LocalDateTime createTime;

    /**
     * @Description: 问题编码
     */
    private String problemCode;

    /**
     * @Description: 问题发生时间
     */
    private LocalDateTime eventTime;
    
    /**
     * @Description: 问题发生时间
     */
    private Integer limit;
    
    /**
     * @Description: 问题发生时间
     */
    private Integer offset;
}
