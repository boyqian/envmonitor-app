package com.upsoft.environmentmonitoring.annotation;

import com.alibaba.fastjson.JSON;
import com.upsoft.environmentmonitoring.domain.po.OperationLog;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.service.OperationLogService;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;
import com.upsoft.environmentmonitoring.utils.JwtUtil;
import com.upsoft.environmentmonitoring.utils.ObjectIdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OperationLogAspect
 * @Description 系统操作日志的切点类
 * @Author WEI WEI
 * @Date 2019/9/27
 * @Version 1.0
 */
@Aspect
@Component
public class OperationLogAspect {

	/**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * @Description: 注入OperationLogService
	 */
	@Autowired
	private OperationLogService operationLogService;

	/**
	 * @Author weiwei
	 * @Description Controller层切点
	 * @Date 17:55 2019/9/27
	 * @Params []
	 * @return void
	 **/
	@Pointcut("@annotation(com.upsoft.environmentmonitoring.annotation.SystemOperationLog)")
	public void systemOperationAspect(){}

	/**
	 * @Author weiwei
	 * @Description 切面环绕通知
	 * @Date 12:42 2019/9/29
	 * @Params 
	 * @return 
	 **/
	@Around("systemOperationAspect()")
	public Object saveOperationLog(ProceedingJoinPoint proceedingJoinPoint){
		// 在方法执行前执行
		logger.info("前置通知开始......");
		try {
			// 在方法执行完成后,并且能够得到正常返回结果时要执行
			BaseResult result = (BaseResult)proceedingJoinPoint.proceed();
			// 当返回结果是正常的才记录日志
			if(result.getCode() == 200 && result.getStatus() == 1){
				OperationLog operationLog = constructOperationLog(proceedingJoinPoint);
				if("/login".equals(request.getServletPath())) {
					Object[] args = proceedingJoinPoint.getArgs();
					operationLog.setOperationUserName(args[0].toString());
					// 基于安全性考虑为了防止密码泄露,登录操作不记录操作内容
					operationLog.setOperationContent(null);
				} else {
					User user = getUserFromToken();
					operationLog.setOperationUserId(user.getUserId());
					operationLog.setOperationUserName(user.getUserName());
				}
				operationLogService.insertOperationLog(operationLog);
			}
			logger.info("返回通知......");
			return result;
		} catch (Throwable throwable) {
			// 在切点表达式匹配到的方法发生异常时执行
			logger.error("异常通知......");
			throwable.printStackTrace();
		} finally {
			// 后置通知,常用于关闭资源等操作
			logger.info("后置通知......");
		}
		return BaseResultUtil.error(500,"系统内部错误");
	}

	/**
	 * @Author weiwei
	 * @Description 获取操作描述
	 * @Date 13:22 2019/9/29
	 * @Params ProceedingJoinPoint proceedingJoinPoint
	 * @return String
	 **/
	private String getAnnotationDescription(ProceedingJoinPoint proceedingJoinPoint) {
		MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method method = signature.getMethod();
		SystemOperationLog annotation = method.getAnnotation(SystemOperationLog.class);
		return annotation.description();
	}

	/**
	 * @Author weiwei
	 * @Description 获取操作类型（查询,新增, 修改, 删除）
	 * @Date 13:22 2019/9/29
	 * @Params ProceedingJoinPoint proceedingJoinPoint
	 * @return String
	 **/
	private String getAnnotationOperationType(ProceedingJoinPoint proceedingJoinPoint) {
		MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method method = signature.getMethod();
		SystemOperationLog annotation = method.getAnnotation(SystemOperationLog.class);
		return annotation.operationType();
	}

	/**
	 * @Author weiwei
	 * @Description 获取方法参数并转换成json字符串
	 * @Date 13:22 2019/9/29
	 * @Params ProceedingJoinPoint proceedingJoinPoint
	 * @return String
	 **/
	private String getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
		// 获取参数名
		String[] argNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
		// 获取参数值
		Object[] args = proceedingJoinPoint.getArgs();
		Map argsMap = new HashMap();
		if(argNames != null && argNames.length > 0) {
			for(int i = 0; i < argNames.length; i++){
				argsMap.put(argNames[i], args[i]);
			}
		}
		return JSON.toJSONString(argsMap);
	}

	/**
	 * @Author weiwei
	 * @Description 从token中获取User信息
	 * @Date 13:52 2019/9/29
	 * @Params []
	 * @return User
	 **/
	private User getUserFromToken (){
		String token = request.getHeader("token");
		return JwtUtil.getClaimsValue(token, "user", User.class);
	}

	/**
	 * @Author weiwei
	 * @Description 获取用户真实IP地址，不使用request.getRemoteAddr();
	 * 的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，
	 * 而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130
	 * 用户真实IP为： 192.168.1.110
	 * @Date 13:09 2019/9/29
	 * @Params
	 * @return String
	 **/
	private String getIpAddress(){
		String ip = null;
		// X-Forwarded-For：Squid 服务代理
		String ipAddresses = request.getHeader("X-Forwarded-For");
		// X-Real-IP：nginx服务代理
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("X-Real-IP");
		}
		// Proxy-Client-IP：apache 服务代理
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("Proxy-Client-IP");
		}
		// WL-Proxy-Client-IP：weblogic 服务代理
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("WL-Proxy-Client-IP");
		}
		// HTTP_CLIENT_IP：有些代理服务器
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("HTTP_CLIENT_IP");
		}
		// 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
		if (ipAddresses != null && ipAddresses.length() != 0 && ipAddresses.length() > 15) {
			ip = ipAddresses.split(",")[0];
		}
		// 还是不能获取到，最后再通过request.getRemoteAddr();获取
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ip = request.getRemoteAddr();
			if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		return ip;
	}

	/**
	 * @Author weiwei
	 * @Description 构造操作日志对象
	 * @Date 14:46 2019/9/29
	 * @Params []
	 * @return OperationLog
	 **/
	private OperationLog constructOperationLog(ProceedingJoinPoint proceedingJoinPoint) {
		OperationLog operationLog = new OperationLog();
		String operationTitle = getAnnotationDescription(proceedingJoinPoint);
		String operationType = getAnnotationOperationType(proceedingJoinPoint);
		String operationTableName = proceedingJoinPoint.getTarget().getClass().getName() + "." + proceedingJoinPoint.getSignature().getName();
		String operationContent = getRequestParams(proceedingJoinPoint);
		operationLog.setOperationLogId(ObjectIdUtil.id())
					.setOperationTitle(operationTitle)
					.setOperationType(operationType)
					.setOperationUri(request.getServletPath())
					.setOperationRequestIp(getIpAddress())
					.setOperationTableName(operationTableName)
					.setOperationContent(operationContent)
					.setCreateTime(LocalDateTime.now());
		return operationLog;
	}

}
