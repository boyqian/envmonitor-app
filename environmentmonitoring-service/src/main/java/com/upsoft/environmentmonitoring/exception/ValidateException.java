package com.upsoft.environmentmonitoring.exception;

import lombok.*;

/**
 * @ClassName ValidateException
 * @Description 自定义参数校验异常
 * @Author wei wei
 * @Date 2018/12/13 15:48
 * @Version 1.0
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateException extends RuntimeException {

	/**
	 * @Description: 服务器响应状态码
	 */
	private Integer code;

	/**
	 * @Description: 错误信息
	 */
	private String errorMessage;
}
