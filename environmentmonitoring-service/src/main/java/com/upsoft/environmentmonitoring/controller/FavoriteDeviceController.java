package com.upsoft.environmentmonitoring.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.upsoft.environmentmonitoring.domain.po.FavoriteDevice;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.service.FavoriteDeviceService;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;
import com.upsoft.environmentmonitoring.utils.JwtUtil;
import com.upsoft.environmentmonitoring.utils.ObjectIdUtil;

/**
 * @ClassName FavoriteDeviceController
 * @Description FavoriteDevice Restful API接口
 * @Author Wang wanqian
 * @Date 2019-11-28
 * @Version 1.0
 */
@RestController
@Validated
@Api(value = "FavoriteDevice 视频收藏管理接口")
public class FavoriteDeviceController {

	/**
	 * @Description: 注入FavoriteDeviceService
	 */
	@Autowired
	private FavoriteDeviceService favoriteDeviceService;
	
	/**
	 * @Description: 注入HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	/**
	 * @Author Wang wanqian
	 * @Description 添加修改收藏视频
	 * @Date 2019-11-01
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/favoriteDevice/updateStatus")
	@ApiOperation(value = "favoriteDevice添加修改收藏视频",notes="必传参数deviceId,channelId,deviceName,deviceIp必传参数isDeleted,\"0\"表示收藏，\"1\"表示取消收藏")
	public BaseResult updateStatus(@RequestBody FavoriteDevice favoriteDevice){
		if(null==favoriteDevice.getDeviceId()||null==favoriteDevice.getChannelId()
				||null==favoriteDevice.getDeviceName()||null==favoriteDevice.getDeviceIp()){
			return BaseResultUtil.error("缺少参数");
		}
		String token = httpServletRequest.getHeader("token");
		String msg=favoriteDeviceService.doFavoriteDevice(token,favoriteDevice);
		return BaseResultUtil.success(msg);
	}
}
