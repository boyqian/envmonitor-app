package com.upsoft.environmentmonitoring.domain.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName BaseResult
 * @Description Controller接口统一返回结果封装
 * @Author wei wei
 * @Date 2018/12/11 9:52
 * @Version 1.0
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> implements Serializable {

	/**
	 * @Description: 服务器响应返回的状态码。例如 : 200 : 请求成功。 500 : 服务器内部错误。
	 */
	private Integer code;

	/**
	 * @Description: 后端逻辑处理结果状态，0表示失败， 1表示成功
	 */
	private Integer status;

	/**
	 * @Description: 服务器返回的提示信息 ,主要返回给用户看。
	 */
	private String message;

	/**
	 * @Description: 服务器返回的数据内容。
	 */
	private T data;
}
