package com.upsoft.environmentmonitoring.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.upsoft.environmentmonitoring.dao.UserMapper;
import com.upsoft.environmentmonitoring.service.UserService;

/**
 * @ClassName UserServiceImpl
 * @Description  服务实现类
 * 如果有方法需要使用到@Transactional注解,需要在类或方法上加入@DS注解注入对应的数据源.
 * 如果类和方法都有@DS注解,方法上的的注解会覆盖类的注解,并且只支持单库事务.
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@Service
@DS("user")
public class UserServiceImpl implements UserService {

   /**
	 * @Description: 注入UserMapper
	 */
   @Autowired
   private UserMapper userMapper;

    /**
	 * @Author Wei Wei
	 * @Description 查询单个User
	 * @Date 2019-10-21
	 * @Params [User]
	 * @return User
	 **/
    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }

   /**
	 * @Author Wei Wei
	 * @Description 修改User
	 * @Date 2019-10-21
	 * @Params [User]
	 * @return void
	 **/
    @Override
    @Transactional
    public void updateUser(User user) {
		userMapper.updateUser(user);
    }

}

