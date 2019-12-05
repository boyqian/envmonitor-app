package com.upsoft.environmentmonitoring.service;

import java.util.List;

import com.upsoft.environmentmonitoring.EnvironmentmonitoringApplication;
import com.upsoft.environmentmonitoring.domain.po.Problem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnvironmentmonitoringApplication.class)
public class ProblemServiceTest {

	@Autowired
	private ProblemService problemService;

	@Test
	public void getProblem() {
		Problem problem = problemService.getProblem(new Problem().setProblemId("0c3b8e40eeeb407cbe55415573d8f08b"));
		assertNotNull(problem);
		assertEquals("0c3b8e40eeeb407cbe55415573d8f08b", problem.getProblemId());
	}
	
	@Test
	public void getProblemList(){
		List<Problem> problemList=problemService.getProblemList(new Problem().setXzsq("遂宁市").setXzsqCode("5109"));
		assertNotNull(problemList);
		for(int i=0;i<problemList.size();i++){
			System.out.println("id:"+problemList.get(i).getProblemId()
							  +"问题详情:"+problemList.get(i).getProblemDetail()
							  +"问题类型:"+problemList.get(i).getProblemTypeName());
		}
	}
}