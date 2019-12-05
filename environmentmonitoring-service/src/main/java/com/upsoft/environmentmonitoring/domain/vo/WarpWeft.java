package com.upsoft.environmentmonitoring.domain.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName WarpWeft
 * @Description 经纬度
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class WarpWeft implements Serializable{

	private static final long serialVersionUID = 1L;

	 /**
     * @Description: 经度
     */
    private String lng;
    
    /**
     * @Description: 纬度
     */
    private String lat;
}
