package com.upsoft.environmentmonitoring.utils;

import java.security.MessageDigest;

/**
 * @ClassName MD5Util
 * @Description 基于MD5的加密工具类
 * @Author Wang Wanqian
 * @Date 2019/10/18
 * @Version 1.0
 */
public class MD5Util {
	/**
     * @Description: 密文
     */
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d","e", "f" };
	
	/**
     * @Description
     *  byte类型转16进制将1个字节（1 byte = 8 bit）转为 2个十六进制位
     *  1个16进制位 = 4个二进制位 （即4 bit）
     *  转换思路：最简单的办法就是先将byte转为10进制的int类型，
     *  然后将十进制数转十六进制
     * @Date 2019/9/18
     * @Params [byte b]
     * @return java.lang.String
     **/
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
     * @Author Wang Wanqian
     * @Description 将字节数组里每个字节转成2个16进制位的字符串后拼接起来
     * @Date 2019/9/18
     * @Params [byte b[]]
     * @return java.lang.String
     **/
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
     * @Author Wang Wanqian
     * @Description  第一次MD5加密
     * @Date 2019/9/18
     * @Params [String origin, String charsetname]
     * @return java.lang.String
     **/
	private static String MD5_32(String origin, String charsetname) {
		String resultString = null;
		try {
			// 1,创建MessageDigest对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 2,向MessageDigest传送要计算的数据;传入的数据需要转化为指定编码的字节数组
			md.update(origin.getBytes(charsetname));
			// 3,计算摘要
			byte[] bytesResult = md.digest();
			// 4,将字节数组转换为16进制位
			resultString = byteArrayToHexString(bytesResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	/**
     * @Author Wang Wanqian
     * @Description  第二次MD5加密
     * @Date 2019/9/18
     * @Params [String str]
     * @return java.lang.String
     **/
	public static String md5ByTwice(String str){
		return MD5_32(MD5_32(str,"utf-8"),"utf-8");
	}

	/**
     * @Author Wang Wanqian
     * @Description  判断数据库中的字段是否是两次加密后的信息
     * @Date 2019/9/18
     * @Params [String str]
     * @return java.lang.String
     **/
	public static boolean passwordIsTrue(String str,String password){
		String md5=md5ByTwice(str);
		return password.equals(md5);
	}
}
