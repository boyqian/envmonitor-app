package com.upsoft.environmentmonitoring.service;

import java.util.List;

import com.upsoft.environmentmonitoring.domain.po.FavoriteDevice;

/**
 * @ClassName FavoriteDeviceService
 * @Description 收藏视频服务
 * @Author Wang wanqian
 * @Date 2019-11-28
 * @Version 1.0
 */
public interface FavoriteDeviceService {

	/**
	 * @Author Wang wanqian
	 * @Description 新增视频收藏
	 * @Date 2019-11-28
	 * @Params []
	 * @return void
	 **/
	void insertFavoriteDevice(FavoriteDevice favoriteDevice);
	
	/**
	 * @Author Wang wanqian
	 * @Description 取消视频收藏
	 * @Date 2019-11-28
	 * @Params [favoriteDevice]
	 * @return void
	 **/
	void updateFavoriteDevice(FavoriteDevice favoriteDevice);
	
	/**
	 * @Author Wang wanqian
	 * @Description 查看视频收藏列表
	 * @Date 2019-11-28
	 * @Params [favoriteDevice]
	 * @return List<FavoriteDevice>
	 **/
	List<FavoriteDevice> getFavoriteDevice(FavoriteDevice favoriteDevice);
	
	/**
	 * @Author Wang wanqian
	 * @Description 视频收藏服务
	 * @Date 2019-12-04
	 * @Params [token，favoriteDevice]
	 * @return String
	 **/
	String doFavoriteDevice(String token,FavoriteDevice favoriteDevice);
}
