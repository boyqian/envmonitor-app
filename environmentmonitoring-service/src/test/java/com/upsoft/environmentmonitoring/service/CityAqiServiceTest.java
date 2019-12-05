package com.upsoft.environmentmonitoring.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.upsoft.environmentmonitoring.EnvironmentmonitoringApplication;
import com.upsoft.environmentmonitoring.domain.po.CityAqi;
import com.upsoft.environmentmonitoring.domain.po.TargetDays;
import com.upsoft.environmentmonitoring.domain.vo.AqiCalendarVO;
import com.upsoft.environmentmonitoring.domain.vo.CityAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.GoodDays;
import com.upsoft.environmentmonitoring.domain.vo.PrimaryPollutant;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnvironmentmonitoringApplication.class)
public class CityAqiServiceTest {

	/**
	 * @Description: 注入CityAqiService
	 */
	@Resource(name = "cityAqiServiceImpl")
	private CityAqiService cityAqiService;
	
	/**
	 * @Description: 注入CityAqiHourDayService
	 */
	@Resource(name = "cityAqiHourDayServiceImpl")
	private CityAqiHourDayService cityAqiHourDayService;
	/**
	 * @Description 测试首页达标天数接口
	 * @author Wang wanqian
	 * @Date 2019-11-15 11:04
	 * */
	@Test
	public void testGetTargetDays() {
		CityAqi cityAqi=new CityAqi();
		cityAqi.setCityCode("5109");//5109指代遂宁市的城市code
		TargetDays target=cityAqiService.getTargetDays(cityAqi);
		System.out.println("达标天数："+target.getDDays());
	}
	
	/**
	 * @Description 测试首页近24小时或30天接口数据
	 * @author Wang wanqian
	 * @Date 2019-11-15 13:42
	 * */
	@Test
	public void getCityAqiByOperation(){
		CityAqi cityAqi=new CityAqi();
		cityAqi.setCityCode("5109");
		cityAqi.setCityAqiType("H");//查询近24小时的数据
		List<CityAqi> list=cityAqiService.getProblemByOperation(cityAqi);
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				System.out.println("Aqi值："+list.get(i).getAqi()
						+"时间："+list.get(i).getPublishDateTime());
			}
		}
		List<CityAqiVO> listVo=cityAqiHourDayService.getCityAqiHourDay(cityAqi);
		System.out.println(listVo.toString());
		
	}
	
	/**
	 * @Description 测试首页本年或本月的接口数据
	 * @author Wang wanqian
	 * @Date 2019-11-15 13:42
	 * */
	@Test
	public void getGoodDaysByOperation(){
		CityAqi cityAqi=new CityAqi();
		cityAqi.setCityCode("5109");
		cityAqi.setCityAqiType("H");//查询本月的优良天数数据
		GoodDays goodDays=cityAqiService.getGoodDaysByOperation(cityAqi);
		System.out.println("优天数："+goodDays.getExcellent()+
				"良天数："+goodDays.getGood()+
				"轻度污染天数："+goodDays.getLight()+
				"中度污染天数："+goodDays.getModerate()+
				"重度污染天数："+goodDays.getSevere()+
				"严重污染天数："+goodDays.getSerious()+
				"优相较于去年同月环比："+goodDays.getExcellentDays());
	}
	
	/**
	 * @Description 测试首页-获取aqi和首要污染物
	 * @author Wang wanqian
	 * @Date 2019-11-19 14:42
	 * */
	@Test
	public void getPrimaryPollutant(){
		CityAqi cityAqi=new CityAqi();
		cityAqi.setCityCode("5109");//5109指代遂宁市的城市code
		PrimaryPollutant pp=cityAqiService.getPrimaryPollutant(cityAqi);
		System.out.println("Aqi:"+pp.getAqi()+
				"首要污染物："+pp.getPrimaryPollutant());
	}
	
	@Test
	public void getAqiCalendar(){
		CityAqi cityAqi=new CityAqi();
		cityAqi.setCityCode("5109")//5109指代遂宁市的城市code
		.setStandby1("2019-11-01");
		List<AqiCalendarVO> list=cityAqiService.getAqiCalendar(cityAqi);
		System.out.println(list.toString());
	}
}
