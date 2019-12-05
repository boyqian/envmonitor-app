package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName User
 * @Description 用户基本信息
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Description: 用户ID
     */
    private String userId;

    /**
     * @Description: 用户名
     */
    private String userName;

    /**
     * @Description: 密码
     */
    private String password;

    /**
     * @Description: 昵称
     */
    private String name;

    /**
     * @Description: 是否可用
     */
    private BigDecimal enable;

    /**
     * @Description: 创建人ID
     */
    private String creatorId;

    /**
     * @Description: 创建时间
     */
    private LocalDateTime createTime;

    /**
     * @Description: 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * @Description: 年龄
     */
    private String age;

    /**
     * @Description: 电话
     */
    private String tel;

    /**
     * @Description: 性别
     */
    private String sex;

    /**
     * @Description: 邮箱
     */
    private String email;

    /**
     * @Description: TODO
     */
    private String sourceUserId;

    /**
     * @Description: TODO
     */
    private String sourcePlat;

    /**
     * @Description: 用户类型
     */
    private BigDecimal userType;

    /**
     * @Description: TODO
     */
    private BigDecimal isInner;

    /**
     * @Description: TODO
     */
    private String innerRoleId;

    /**
     * @Description: 修改人ID
     */
    private String updaterId;

    /**
     * @Description: 版本时间
     */
    private BigDecimal versionTime;

    /**
     * @Description: 删除标示
     */
    private BigDecimal delFlag;

    /**
     * @Description: TODO
     */
    private String extInfo;

    /**
     * @Description: TODO
     */
    private String bizRule;

    /**
     * @Description: 行政区划编码
     */
    private String divisionCode;

    /**
     * @Description: 行政区划名称
     */
    private String divisionName;


}
