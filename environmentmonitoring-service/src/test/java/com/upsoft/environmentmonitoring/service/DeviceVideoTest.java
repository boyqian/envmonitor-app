package com.upsoft.environmentmonitoring.service;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.upsoft.environmentmonitoring.EnvironmentmonitoringApplication;
import com.upsoft.environmentmonitoring.domain.po.StationVideoBean;
import com.upsoft.environmentmonitoring.constant.StationVideo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnvironmentmonitoringApplication.class)
public class DeviceVideoTest {
	
	@Autowired
	private StationVideoBean svBean;
	
	@Test
	public void getDeviceVideo() throws Exception{
		String videoName="邦泰河东院子";
		Map<String,String> map=new HashMap<String,String>();
		if(StationVideo.STATION_JINKE.getVideoName().equals(videoName)){
			String a=svBean.getMaps().get(StationVideo.STATION_JINKE.getStationName());
			String[] b=a.split("&");
			map.put(b[0], b[1]);
			System.out.println(map.get(videoName));
		}else if(StationVideo.STATION_MINJIE.getVideoName().equals(videoName)){
			String a=svBean.getMaps().get(StationVideo.STATION_MINJIE.getStationName());
			String[] b=a.split("&");
			map.put(b[0], b[1]);
			System.out.println(map.get(videoName));
		}else if(StationVideo.STATION_BANGTAI.getVideoName().equals(videoName)){
			String a=svBean.getMaps().get(StationVideo.STATION_BANGTAI.getStationName());
			String[] b=a.split("&");
			map.put(b[0], b[1]);
			System.out.println(map.get(videoName));
		}
	}
}
