package com.upsoft.environmentmonitoring.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName JwtTokenUtil
 * @Description Json Web Token工具类
 * @Author Wei Wei
 * @Date 2019/9/8
 * @Version 1.0
 */
public class JwtUtil {

    /**
     * @Description: 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * @Description: Token加密Key
     */
    private static String encryKey = "intelligenteye";

    /**
     * @Author weiwei
     * @Description 生成Token
     * @Date 9:09 2019/9/26
     * @Params [claimMaps, isJsonMpas, encryKey, secondTimeOut]
     * @return java.lang.String
     **/
    public static String createToken(Map claimMaps) {
        long currentTime = System.currentTimeMillis();
        if (claimMaps != null && !claimMaps.isEmpty()){
            claimMaps.forEach((key, val) -> {
                claimMaps.put(key, JSON.toJSONString(val));
            });
        }
        return Jwts.builder()
                .setId(ObjectIdUtil.id())
                // 签发时间
                .setIssuedAt(new Date(currentTime))
                // 说明
                .setSubject("intelligenteye")
                // 系统管理员
                .setIssuer("system")
                // 数据压缩方式
                .compressWith(CompressionCodecs.GZIP)
                // 加密方式
                .signWith(SignatureAlgorithm.HS256, encryKey)
                // 过期时间戳
                .setExpiration(new Date(currentTime + 1000 * 3600))
                // 自定义claim信息
                .addClaims(claimMaps)
                .compact();
    }

    /**
     * @Author weiwei
     * @Description 验证Token是否过期
     * @Date 17:10 2019/9/26
     * @Params [token]
     * @return boolean
     **/
    public static boolean isExpiration(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(encryKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException ex) {
            return true;
        }
    }

    /**
     * @Author weiwei
     * @Description 获取token中的claims信息里自定义存放的Map数据，如用户信息
     * @Date 17:38 2019/9/26
     * @Params [String token, String key]
     * @return Object
     **/
    public static <T> T getClaimsValue(String token, String key, Class<T> tClass) {
        try {
            String strJson = Jwts.parser()
                    .setSigningKey(encryKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .get(key)
                    .toString();
            return JSON.parseObject(strJson, tClass);
        } catch (Exception ex) {
            return null;
        }
    }

}
