package com.upsoft.environmentmonitoring.dao;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;

import com.upsoft.environmentmonitoring.domain.po.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;

/**
 * @ClassName ProblemMapper
 * @Description 问题表（报警） Mapper 接口
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
@Repository
@DS("aqm")
public interface ProblemMapper extends BaseMapper<Problem> {

	/**
	 * @Author Wei Wei
	 * @Description 查询单个Problem
	 * @Date 2019-10-21
	 * @Params [Problem]
	 * @return Problem
	 **/
    Problem getProblem(Problem problem);

    /**
	 * @Author Wang wanqian
	 * @Description 首页-查询3条定位城市下的预警信息，没有则返回空
	 * @Date 2019-11-14
	 * @Params [Problem]
	 * @return List<Problem>
	 **/
    List<Problem> getProblemList(Problem problem);
}
