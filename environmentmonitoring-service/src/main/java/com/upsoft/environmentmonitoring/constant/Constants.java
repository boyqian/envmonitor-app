package com.upsoft.environmentmonitoring.constant;

/**
 * @ClassName Constant
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/13 9:19
 * @Version 1.0
 */

public class Constants {

	/******************************* 状态码常量列表 *********************************/

	/**
	 * @Description: 成功
	 */
	public static final Integer REQUEST_SUCCESS_CODE = 200;

	/**
	 * @Description: 无效或不合法的参数
	 */
	public static final Integer INVALID_PARAMETER_CODE = 400;

	/**
	 * @Description: 登录失败
	 */
	public static final Integer LOGIN_FAIL_CODE = 401;

	/**
	 * @Description: 没有检测到Token
	 */
	public static final Integer INVALID_TOKEN_CODE = 402;

	/**
	 * @Description: Token已失效
	 */
	public static final Integer TOKEN_EXPIRATION_CODE = 419;

	/**
	 * @Description: 请求地址错误
	 */
	public static final Integer BAD_REQUEST_CODE = 404;

	/**
	 * @Description: 服务器内部错误
	 */
	public static final Integer INTERNAL_SERVER_ERROR_CODE = 500;
	
	/**
	 * @Description: 因子的数量，So2,No2,co,o3,pm2.5,pm10共6种
	 */
	public static final Integer FACTOR_COUNT=6;
	
	/**
	 * @Description: 24小时数据结构
	 */
	public static final Integer HOUR_24=24;
	
	/**
	 * @Description: 30天数据结构
	 */
	public static final Integer DAY_30=30;
	
	/**
	 * @Description: So2因子
	 * */
	public static final String FACTOR_SO2="SO2";
	
	/**
	 * @Description: NO2因子
	 * */
	public static final String FACTOR_NO2="NO2";
	
	/**
	 * @Description: CO因子
	 * */
	public static final String FACTOR_CO="CO";
	
	/**
	 * @Description: O3因子
	 * */
	public static final String FACTOR_O3="O3";
	
	/**
	 * @Description: PM25因子
	 * */
	public static final String FACTOR_PM25="PM25";
	
	/**
	 * @Description: PM10因子
	 * */
	public static final String FACTOR_PM10="PM10";
	
	/**
	 * @Description: 因子初始化数值为0
	 * */
	public static final String FACTOR_VALUE="0";
	
	/**
	 * @Description: 收藏成功
	 * */
	public static final String COLLECTION_SUCCESS="收藏成功";
	
	/**
	 * @Description: 取消收藏成功
	 * */
	public static final String CANCEL_COLLECTION_SUCCESS="取消收藏成功";
}
