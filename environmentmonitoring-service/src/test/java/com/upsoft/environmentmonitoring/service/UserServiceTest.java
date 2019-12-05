package com.upsoft.environmentmonitoring.service;

import com.upsoft.environmentmonitoring.EnvironmentmonitoringApplication;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.upsoft.environmentmonitoring.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =EnvironmentmonitoringApplication.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void getUser() {
		User user = new User().setUserName("system");
		User u = userService.getUser(user);
		assertEquals("system", u.getUserName());
	}

	@Test
	public void updateUser() {
		String pwd = "000000";
		String md5pwd = MD5Util.md5ByTwice(pwd);
		User user = new User().setUserName("system").setPassword(md5pwd);
		userService.updateUser(user);
		User u = userService.getUser(user);
		assertEquals(md5pwd, u.getPassword());
	}
}