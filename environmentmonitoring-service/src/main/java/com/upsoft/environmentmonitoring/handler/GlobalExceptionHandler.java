package com.upsoft.environmentmonitoring.handler;

import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.exception.BaseException;
import com.upsoft.environmentmonitoring.exception.ValidateException;
import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常捕获类
 * @Author wei wei
 * @Date 2018/12/11 12:23
 * @Version 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @Author weiwei
	 * @Description TODO
	 * @Date 12:32 2018/12/11
	 * @Params [Exception e]
	 * @return BaseResult<T>
	 **/
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public <T> BaseResult<T> handle(Exception e) {
		// 自定义的BaseException类异常返回处理
		if (e instanceof BaseException) {
			Integer code = null;
			BaseException exception = (BaseException) e;
			if (!Objects.equals(0, exception.getCode())) {
				code = exception.getCode();
			}
			return BaseResultUtil.error(code, exception.getErrorMessage());
		}

		// 单个参数自动参数校验异常处理
		if (e instanceof ConstraintViolationException) {
			for(ConstraintViolation violation: ((ConstraintViolationException) e).getConstraintViolations()){
				return BaseResultUtil.error(Constants.INVALID_PARAMETER_CODE, violation.getMessage());
			}
		}

		// 对象自动参数校验异常处理
		if (e instanceof ValidateException) {
			Integer code = null;
			ValidateException exception = (ValidateException) e;
			if (!Objects.equals(0, exception.getCode())) {
				code = exception.getCode();
			}
			return BaseResultUtil.error(code, exception.getErrorMessage());
		}

		// 404异常返回处理
		if (e instanceof NoHandlerFoundException) {
			return BaseResultUtil.error(Constants.BAD_REQUEST_CODE, "请求地址有误,请检查访问地址" );
		}

		// 500异常返回处理
		return BaseResultUtil.error(Constants.INTERNAL_SERVER_ERROR_CODE, "服务器内部错误,请稍后再试");
	}

}
