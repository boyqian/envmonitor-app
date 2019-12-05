package com.upsoft.environmentmonitoring.domain.po;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName FavoriteDevice
 * @Description 视频收藏
 * @Author Wang wanqian
 * @Date 2019-11-28
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class FavoriteDevice implements Serializable{

	private static final long serialVersionUID = 1L;

	 /**
     * @Description: 主键ID
     */
	private String favoriteDeviceId;
	
	/**
     * @Description: 设备Id
     */
	private String deviceId;
	
	/**
     * @Description: 通道ID
     */
	private String channelId;
	
	/**
     * @Description: 设备名称
     */
	private String deviceName;
	
	/**
     * @Description: 设备IP
     */
	private String deviceIp;
	
	/**
     * @Description: 用户id
     */
	private String userId;
	
	/**
     * @Description: 用户名
     */
	private String userName;
	
	/**
     * @Description: 创建者
     */
	private String creator;
	
	/**
     * @Description: 修改者
     */
	private String editor;
	
	/**
     * @Description: 是否逻辑删除1代表删除，0代表没有删除
     */
	private Integer isDeleted;
	
	/**
     * @Description: 创建时间
     */
	private LocalDateTime createTime;
	
	/**
     * @Description: 更新时间
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
