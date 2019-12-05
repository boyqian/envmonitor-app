package com.upsoft.environmentmonitoring.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.dao.FavoriteDeviceMapper;
import com.upsoft.environmentmonitoring.domain.po.FavoriteDevice;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.upsoft.environmentmonitoring.service.FavoriteDeviceService;
import com.upsoft.environmentmonitoring.utils.JwtUtil;
import com.upsoft.environmentmonitoring.utils.ObjectIdUtil;

/**
 * @ClassName FavoriteDeviceServiceImpl
 * @Description 收藏视频服务实现类
 * @Author Wang wanqian
 * @Date 2019-11-28
 * @Version 1.0
 */
@Service
public class FavoriteDeviceServiceImpl implements FavoriteDeviceService{

	/**
	 * @Description: 注入FavoriteDeviceMapper
	 */
	@Autowired
	private FavoriteDeviceMapper favoriteDeviceMapper;
	
	/**
	 * @Author Wang wanqian
	 * @Description 新增视频收藏
	 * @Date 2019-11-28
	 * @Params [favoriteDevice]
	 * @return void
	 **/
	@Override
	@Transactional
	public void insertFavoriteDevice(FavoriteDevice favoriteDevice) {
		favoriteDeviceMapper.insertFavoriteDevice(favoriteDevice);
		
	}

	/**
	 * @Author Wang wanqian
	 * @Description 更新视频收藏
	 * @Date 2019-11-28
	 * @Params [favoriteDevice]
	 * @return void
	 **/
	@Override
	@Transactional
	public void updateFavoriteDevice(FavoriteDevice favoriteDevice) {
		favoriteDeviceMapper.updateFavoriteDevice(favoriteDevice);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 获取视频收藏列表
	 * @Date 2019-11-28
	 * @Params [favoriteDevice]
	 * @return List<FavoriteDevice>
	 **/
	@Override
	public List<FavoriteDevice> getFavoriteDevice(FavoriteDevice favoriteDevice) {
		return favoriteDeviceMapper.getFavoriteDevice(favoriteDevice);
	}

	/**
	 * @Author Wang wanqian
	 * @Description 视频收藏服务
	 * @Date 2019-12-04
	 * @Params [token]
	 * @return void
	 **/
	@Override
	public String doFavoriteDevice(String token,FavoriteDevice favoriteDevice) {
		User user = JwtUtil.getClaimsValue(token, "user", User.class);
		FavoriteDevice fd=new FavoriteDevice();
		fd.setUserId(user.getUserId())
		.setUserName(user.getUserName())
		.setDeviceId(favoriteDevice.getDeviceId());
		List<FavoriteDevice> list=favoriteDeviceMapper.getFavoriteDevice(fd);
		if(null!=list&&list.size()>0){
			// 更新收藏视频
			favoriteDevice.setUserId(user.getUserId())
			.setUserName(user.getUserName())
			.setEditor(user.getUserName())
			.setUpdateTime(LocalDateTime.now());
			favoriteDeviceMapper.updateFavoriteDevice(favoriteDevice);
			if(null!=favoriteDevice.getIsDeleted()&&favoriteDevice.getIsDeleted().equals(1)){
				return Constants.CANCEL_COLLECTION_SUCCESS;
			}else{
				return Constants.COLLECTION_SUCCESS;
			}
		}else{
			// 第一次添加收藏视频
			favoriteDevice.setFavoriteDeviceId(ObjectIdUtil.id())
			.setUserId(user.getUserId())
			.setUserName(user.getUserName())
			.setCreateTime(LocalDateTime.now())
			.setCreator(user.getUserName())
			.setIsDeleted(0);
			favoriteDeviceMapper.insertFavoriteDevice(favoriteDevice);
			return Constants.COLLECTION_SUCCESS;
		}
		
	}

}
