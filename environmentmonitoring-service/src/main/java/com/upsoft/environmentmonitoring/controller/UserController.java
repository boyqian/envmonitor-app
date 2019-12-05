package com.upsoft.environmentmonitoring.controller;

import com.upsoft.environmentmonitoring.annotation.SystemOperationLog;
import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.utils.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import com.upsoft.environmentmonitoring.domain.po.ModifyPassword;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.upsoft.environmentmonitoring.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description User Restful API接口
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@RestController
@Validated
@Api(value = "User管理接口")
public class UserController {
   /**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * @Description: 注入UserService
	 */
	@Autowired
	private UserService userService;

	/**
	 * @Author weiwei
	 * @Description 登录
	 * @Date 14:18 2019/9/27
	 * @Params
	 * @return
	 **/
	@PostMapping("/login")
	@ApiOperation(value = "登录")
	@SystemOperationLog(description = "登录", operationType = "查询")
	public BaseResult login(@RequestParam String username, @RequestParam String password){
		User userParam = new User();
		userParam.setUserName(username).setPassword(MD5Util.md5ByTwice(password));
		User user = userService.getUser(userParam);
		if(user == null) {
			return BaseResultUtil.error(Constants.LOGIN_FAIL_CODE, "用户名或密码错误!");
		}else {
				Map claimsMap = new HashMap();
				claimsMap.put("user", user);
				String token = JwtUtil.createToken(claimsMap);
				return BaseResultUtil.success(token);
		}
	}

	/**
	 * @Author Wei Wei
	 * @Description 修改密码
	 * @Date 2019-10-21
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/user/modifyUserPassword")
	@ApiOperation(value = "修改密码")
	@SystemOperationLog(description = "修改密码", operationType = "修改")
	public BaseResult updateUser(@RequestBody ModifyPassword password){
		// 从http请求头中取出token
		String token = httpServletRequest.getHeader("token");
		User user = JwtUtil.getClaimsValue(token, "user", User.class);
		if(user.getPassword().equals(MD5Util.md5ByTwice(password.getOldPassword()))) {
			user.setPassword(MD5Util.md5ByTwice(password.getNewPassword()));
			userService.updateUser(user);
		} else {
			return BaseResultUtil.error("原密码错误！");
		}
		return BaseResultUtil.success("更新成功");
	}

}
