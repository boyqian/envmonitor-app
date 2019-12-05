package com.upsoft.environmentmonitoring.utils;

import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.domain.vo.BaseResult;

/**
 * @ClassName BaseResultUtil
 * @Description 统一返回结果集工具类
 * @Author wei wei
 * @Date 2018/12/11 10:04
 * @Version 1.0
 */
public class BaseResultUtil {

	/**
	 * @Author weiwei
	 * @Description 请求成功返回数据
	 * @Date 10:10 2018/12/11
	 * @Params [data]
	 * @return BaseResult<T>
	 **/
	public static <T> BaseResult<T> success(T data) {
		return new BaseResult(Constants.REQUEST_SUCCESS_CODE, 1, "请求成功", data);
	}

	/**
	 * @Author weiwei
	 * @Description 服务器请求成功，但逻辑处理失败，譬如请求参数校验失败
	 * @Date 10:10 2018/12/11
	 * @Params [String errorMessage]
	 * @return BaseResult<T>
	 **/
	public static <T> BaseResult<T> error(String errorMessage) {
		return new BaseResult(Constants.REQUEST_SUCCESS_CODE, 0, errorMessage, null);
	}

	/**
	 * @Author weiwei
	 * @Description 服务器请求失败，例如404或500等类型的错误
	 * @Date 10:17 2018/12/11
	 * @Params [Integer code, String errorMessage]
	 * @return BaseResult<T>
	 **/
	public static <T> BaseResult<T> error(Integer code, String errorMessage) {
		return new BaseResult(code, 0, errorMessage, null);
	}

}
