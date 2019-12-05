package com.upsoft.environmentmonitoring.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description  Mapper 接口
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@Repository
@DS("user")
public interface UserMapper extends BaseMapper<User> {

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
