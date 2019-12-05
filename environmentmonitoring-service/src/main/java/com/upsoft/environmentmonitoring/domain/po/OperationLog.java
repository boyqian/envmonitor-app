package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName OperationLog
 * @Description 操作日志表,  在insert后执行;  在update前后都要执行;  在delete前执行
 * @Author Wei Wei
 * @Date 2019-09-27
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Description: 操作日志ID
     */
    private String operationLogId;

    /**
     * @Description: 操作标题
     */
    private String operationTitle;

    /**
     * @Description: 操作类型(查询,新增, 修改, 删除)
     */
    private String operationType;

    /**
     * @Description: 访问uri地址
     */
    private String operationUri;

    /**
     * @Description: 来访IP
     */
    private String operationRequestIp;

    /**
     * @Description: 操作表名
     */
    private String operationTableName;

    /**
     * @Description: 操作内容
     */
    private String operationContent;

    /**
     * @Description: 操作用户ID
     */
    private String operationUserId;

    /**
     * @Description: 操作用户名
     */
    private String operationUserName;

    /**
     * @Description: 删除标识(0-未删除, 1-已删除)
     */
    private Integer isDeleted;

    /**
     * @Description: 创建时间
     */
    private LocalDateTime createTime;

    /**
     * @Description: 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * @Description: 备用字段1
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
