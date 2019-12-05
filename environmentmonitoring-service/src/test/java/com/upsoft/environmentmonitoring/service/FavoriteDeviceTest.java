package com.upsoft.environmentmonitoring.service;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.upsoft.environmentmonitoring.EnvironmentmonitoringApplication;
import com.upsoft.environmentmonitoring.domain.po.FavoriteDevice;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnvironmentmonitoringApplication.class)
public class FavoriteDeviceTest {

	@Autowired
	private FavoriteDeviceService fdService;
	
	/**
	 * @Description 测试视频收藏接口
	 * @author Wang wanqian
	 * @Date 2019-11-15 11:04
	 * */
	@Test
	public void testDeviceStatus(){
		FavoriteDevice fd=new FavoriteDevice();
		fd.setChannelId("93138909-1537-4e09-a0ad-af5b407296eb").setDeviceId("93138909-1537-4e09-a0ad-af5b407296eb").setDeviceIp("13e5818b-0784-43c6-9471-6b55f694bc2a").
		setDeviceName("邦泰河东院子").setUserId("85d55e7db55d48fb8eeaf11001fa0a51").setUserName("hbj").setIsDeleted(0).setCreator("hbj").setCreateTime(LocalDateTime.now());
		fdService.insertFavoriteDevice(fd);
		// 查看数据库记录
		FavoriteDevice fd2=new FavoriteDevice();
		fd.setChannelId("93138909-1537-4e09-a0ad-af5b407296eb").setDeviceId("93138909-1537-4e09-a0ad-af5b407296eb").setDeviceIp("13e5818b-0784-43c6-9471-6b55f694bc2a").
		setDeviceName("邦泰河东院子").setUserId("85d55e7db55d48fb8eeaf11001fa0a51").setUserName("hbj").setIsDeleted(1);
		fdService.updateFavoriteDevice(fd2);
		// 查看数据库记录
	}
}
