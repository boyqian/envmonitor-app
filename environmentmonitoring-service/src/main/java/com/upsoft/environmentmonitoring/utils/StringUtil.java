package com.upsoft.environmentmonitoring.utils;

/**
 * @ClassName StringUtil
 * @Description 字符串工具类
 * @Author Wang wanqian
 * @Date 2019/12/03 16:14
 * @Version 1.0
 */
public class StringUtil {

	/**
	 * @Author Wang wanqian
	 * @Description 处理数据判断对象是否为null,返回对象的""或toString类型 
	 * @Date 10:13 2019/11/21
	 * @Params [obj]
	 * @return String
	 **/
	public static String valueToString(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
}
