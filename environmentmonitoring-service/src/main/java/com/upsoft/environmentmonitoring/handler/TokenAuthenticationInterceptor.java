package com.upsoft.environmentmonitoring.handler;

import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.exception.BaseException;
import com.upsoft.environmentmonitoring.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName TokenAuthenticationInterceptor
 * @Description Token验证拦截器
 * @Author Administrator
 * @Date 2019/9/27
 * @Version 1.0
 */
public class TokenAuthenticationInterceptor implements HandlerInterceptor {

	/**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Author weiwei
	 * @Description Token验证
	 * @Date 13:38 2019/9/27
	 * @Params [httpServletRequest, httpServletResponse, object]
	 * @return boolean
	 **/
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
		
		// 从http请求头中取出token
		String token = httpServletRequest.getHeader("token");
		// 检测token是否为空
		if(StringUtils.isEmpty(token)) {
			throw new BaseException(Constants.INVALID_TOKEN_CODE, "没有检测到Token");
		}
		// 检测token是否过期
		if(JwtUtil.isExpiration(token)) {
			throw new BaseException(Constants.TOKEN_EXPIRATION_CODE, "Token已失效");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest,
	                       HttpServletResponse httpServletResponse,
	                       Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest,
	                            HttpServletResponse httpServletResponse,
	                            Object o, Exception e) throws Exception {
	}
}