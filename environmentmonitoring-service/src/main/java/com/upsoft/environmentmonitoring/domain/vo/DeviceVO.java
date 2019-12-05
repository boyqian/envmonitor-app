package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName DeviceVO
 * @Description 视频返回实体
 * @Author Wang wanqian
 * @Date 2019-11-28
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class DeviceVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	 /**
     * @Description: 视频Id
     */
	private String id;
	
	 /**
     * @Description: 视频上级pid
     */
	private String pid;
	
	 /**
     * @Description: 视屏通道id
     */
	private String chanid;
	
	 /**
     * @Description: 视频标题title
     */
	private String title;
	
	 /**
     * @Description: 视频地址
     */
	private String addr;
	
	 /**
     * @Description: 视频类型
     */
	private String type;
	
	 /**
     * @Description: 视频用户
     */
	private String user;
	
	 /**
     * @Description: 视频用户密码
     */
	private String passw;
	 /**
     * @Description: 是否共享
     */
	
	private Integer is_shared;
	
	 /**
     * @Description: 是否在线
     */
	private Integer is_online;
	
	 /**
     * @Description: 其他参数
     */
	private String param;
	
	/**
     * @Description: 是否收藏
     */
	private Boolean isKeep=false;
	
}
