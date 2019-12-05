package com.upsoft.environmentmonitoring.service;

import com.upsoft.environmentmonitoring.EnvironmentmonitoringApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnvironmentmonitoringApplication.class)
public class FetchAQIServiceTest {

	@Autowired
	private FetchAQIService fetchAQIService;

	@Test
	public void insertSichuanDayAQI() {
		fetchAQIService.insertSichuanDayAQI();
	}

	@Test
	public void insertSichuanHourAQI() {
		fetchAQIService.insertSichuanHourAQI();
	}
}