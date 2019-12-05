package com.upsoft.environmentmonitoring.service.impl;


import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.environmentmonitoring.domain.po.Problem;
import com.upsoft.environmentmonitoring.dao.ProblemMapper;
import com.upsoft.environmentmonitoring.service.ProblemService;

/**
 * @ClassName ProblemServiceImpl
 * @Description 问题表（报警）服务实现类
 * 如果有方法需要使用到@Transactional注解,需要在类或方法上加入@DS注解注入对应的数据源.
 * 如果类和方法都有@DS注解,方法上的的注解会覆盖类的注解,并且只支持单库事务.
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@Service
@DS("aqm")
public class ProblemServiceImpl implements ProblemService {

   /**
	 * @Description: 注入ProblemMapper
	 */
	@Autowired
	private ProblemMapper problemMapper;

    /**
	 * @Author Wei Wei
	 * @Description 查询单个Problem
	 * @Date 2019-10-21
	 * @Params [Problem]
	 * @return Problem
	 **/
    @Override
    public Problem getProblem(Problem problem) {
        return problemMapper.getProblem(problem);
    }
    
    /**
	 * @Author Wang wanqian
	 * @Description 首页-定位城市的预警信息，限3条，没有则返回空
	 * @Date 2019-11-14
	 * @Params [Problem]
	 * @return List<Problem>
	 **/
	@Override
	public List<Problem> getProblemList(Problem problem) {
		return problemMapper.getProblemList(problem);
	}

}

