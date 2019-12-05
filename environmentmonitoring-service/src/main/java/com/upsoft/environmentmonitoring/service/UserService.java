package com.upsoft.environmentmonitoring.service;

import com.upsoft.environmentmonitoring.domain.po.User;

/**
 * @ClassName UserMapper
 * @Description 服务接口
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
public interface UserService {

	/**
	 * @Author Wei Wei
	 * @Description 查询单个User
	 * @Date 2019-10-21
	 * @Params [User]
	 * @return User
	 **/
    User getUser(User user);

	/**
	 * @Author Wei Wei
	 * @Description 修改User
	 * @Date 2019-10-21
	 * @Params [User]
	 * @return void
	 **/
	void updateUser(User user);

}
