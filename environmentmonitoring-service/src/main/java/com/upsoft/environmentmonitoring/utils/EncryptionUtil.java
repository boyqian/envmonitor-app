package com.upsoft.environmentmonitoring.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName EncryptionUtil
 * @Description 基于Codec的加密工具类
 * @Author Wei Wei
 * @Date 2019/9/8
 * @Version 1.0
 */
public class EncryptionUtil {
    /**
     * @Description: 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);

    /**
     * @Author Wei Wei
     * @Description 盐值SHA265加密
     * @Date 2019/9/8
     * @Params [String encryptValue, String saltValue]
     * @return java.lang.String
     **/
    public static String sha256Hex (String encryptValue, String saltValue){
        return DigestUtils.sha256Hex(StringUtils.join(encryptValue, saltValue));
    }
}
