package com.upsoft.environmentmonitoring.exception;

import lombok.*;

/**
 * @ClassName BaseException
 * @Description 统一异常类定义
 * @Author wei wei
 * @Date 2018/12/11 10:36
 * @Version 1.0
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

	/**
	 * @Description: 服务器响应状态码
	 */
	private Integer code;

	/**
	 * @Description: 错误信息
	 */
	private String errorMessage;

}
