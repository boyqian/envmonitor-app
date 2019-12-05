package com.upsoft.environmentmonitoring.service;

import java.util.List;
import com.upsoft.environmentmonitoring.domain.po.Problem;

/**
 * @ClassName ProblemMapper
 * @Description 问题表（报警）服务接口
 * @Author Wei Wei
 * @Date 2019-10-21
 * @Version 1.0
 */
public interface ProblemService {

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
	 * @Description 首页-定位城市的预警信息，限3条，没有则返回空
	 * @Date 2019-11-14
	 * @Params [Problem]
	 * @return List<Problem>
	 **/
    List<Problem> getProblemList(Problem problem);
}
