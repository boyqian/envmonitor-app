package com.upsoft.environmentmonitoring.service;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.upsoft.environmentmonitoring.EnvironmentmonitoringApplication;
import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.StationAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.StationPlayAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.StationRatioVO;
import com.upsoft.environmentmonitoring.domain.vo.StationReportVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnvironmentmonitoringApplication.class)
public class StationAqiServiceTest {
	
	@Resource(name = "stationAqiServiceImpl")
	private StationAqiService staAqiService;
	
	@Resource(name = "stationPlayAqiServiceImpl")
	private StationPlayAqiService stationPlayAqiService;
	/**
	 * @Author Wang wanqian
	 * @Description 获取监测地图站点分布情况,温馨提示：H和D的返回结构不同
	 * @Date 2019/11/15
	 * 
	 **/
	@Test
	public void getStationAqiByOperation(){
		StationAqi stationAqi=new StationAqi();
		stationAqi.setCityCode("5133").setStandby1("H");//5133指代康定市城市代码，“H”表示返回近一个小时的监测地图分布情况
		List<StationAqiVO> list=staAqiService.getStationAqiByOperation(stationAqi);
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				System.out.println("站点名称："+list.get(i).getStationName()+
						"Aqi监测值："+list.get(i).getAirVal().getAqi()+
						"站点经度："+list.get(i).getGeo().getLng()+
						"站点纬度："+list.get(i).getGeo().getLat());
			}
			
		}
		stationAqi.setCityCode("5109").setStandby1("D");
		List<StationPlayAqiVO> listVO=stationPlayAqiService.request(stationAqi);
		System.out.println(listVO.toString());
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description  监测地图-单个站点不同因子不同时间段折线图
	 * @Date 2019/11/15
	 * 
	 **/
	@Test
	public void getSingleStationAqiThrend(){
		StationAqi stationAqi=new StationAqi();
		stationAqi.setCityCode("5133").setStationCode("1140E");
		List<StationAqiVO> list=staAqiService.getSingleStationAqiThrend(stationAqi);
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				System.out.println("站点名称："+list.get(i).getStationName()+
						"Aqi监测值："+list.get(i).getAirVal().getAqi()+
						"站点经度："+list.get(i).getGeo().getLng()+
						"站点纬度："+list.get(i).getGeo().getLat());
			}
			
		}
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description  统计分析-统计报表
	 * @Date 2019/11/21
	 * 
	 **/
	@Test
	public void getStationReport(){
		StationAqi stationAqi=new StationAqi();
		stationAqi.setCityCode("5109")
		.setStandby2("2019-10-01")
		.setStandby3("2019-11-22");
		List<StationReportVO> list=staAqiService.getStationReport(stationAqi);
		System.out.println(list.toString());
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description  统计分析-站点比对
	 * @Date 2019/11/29
	 * 
	 **/
	@Test
	public void getStationRatio(){
		StationAqi stationAqi=new StationAqi();
		stationAqi.setStationCode("1042E").setStationName("大英县气象局观测站").setStandby5("20191101");
		StationRatioVO svo=staAqiService.getStationRatio(stationAqi);
		System.out.println(svo.toString());
	}
	
}
