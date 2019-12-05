package com.upsoft.environmentmonitoring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.upsoft.environmentmonitoring.domain.po.FavoriteDevice;

/**
 * @ClassName FavoriteDeviceMapper
 * @Description 视频收藏接口
 * @Author Wang wanqian
 * @Date 2019-11-28
 * @Version 1.0
 */
@Repository
@DS("environmentmonitoring")
public interface FavoriteDeviceMapper {

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
	 * @Params []
	 * @return void
	 **/
	void updateFavoriteDevice(FavoriteDevice favoriteDevice);
	
	/**
	 * @Author Wang wanqian
	 * @Description 查看视频收藏列表
	 * @Date 2019-11-28
	 * @Params []
	 * @return List<FavoriteDevice>
	 **/
	List<FavoriteDevice> getFavoriteDevice(FavoriteDevice favoriteDevice);
}
