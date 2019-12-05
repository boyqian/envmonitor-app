package com.upsoft.environmentmonitoring.controller;

import java.util.List;

import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.upsoft.environmentmonitoring.domain.po.Problem;
import com.upsoft.environmentmonitoring.service.ProblemService;

import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProblemController
 * @Description Problem Restful API接口
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@RestController
@Validated
@Api(value = "Problem管理接口")
public class ProblemController {
   /**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入ProblemService
	 */
	@Autowired
	private ProblemService problemService;
	
	/**
	 * @Author Wang wanqian
	 * @Description 首页-定位城市的预警信息，限3条，没有则返回空
	 * @Date 2019-11-14
	 * @Params [Problem]
	 * @return BaseResult
	 **/
	@PostMapping(value = "/problem/getProblemList")
	@ApiOperation(value = "首页-定位城市的预警信息，限3条\n预警通知-预警通知列表",notes="limit表示第几页0表示第一页，offset表示某页多少条,参数status=1表示首页接口返回限制3条，status=0表示预警通知接口返回所有列表，默认status=0")
	public BaseResult getProblemList(@RequestBody Problem problem){
		List<Problem> problemList=problemService.getProblemList(problem);
		return BaseResultUtil.success(problemList);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 预警通知-根据问题id返回问题详情
	 * @Date 2019-11-19
	 * @Params [Problem]
	 * @return BaseResult
	 **/
	@PostMapping(value = "/problem/getProblemInfo")
	@ApiOperation(value = "预警通知-根据问题id返回问题详情",notes="参数传问题problemId")
	public BaseResult getProblem(@RequestBody Problem problem){
		Problem pro=problemService.getProblem(problem);
		return BaseResultUtil.success(pro);
	}
}
