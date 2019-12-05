package com.upsoft.environmentmonitoring.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.upsoft.environmentmonitoring.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.upsoft.environmentmonitoring.domain.po.OperationLog;
import com.upsoft.environmentmonitoring.dao.OperationLogMapper;

/**
 * @ClassName OperationLogServiceImpl
 * @Description 操作日志服务实现类
 * 在insert后执行;  在update前后都要执行;  在delete前执行
 * 如果有方法需要使用到@Transactional注解,需要在类或方法上加入@DS注解注入对应的数据源.
 * 如果类和方法都有@DS注解,方法上的的注解会覆盖类的注解,并且只支持单库事务.
 * @Author Wei Wei
 * @Date 2019-09-27
 * @Version 1.0
 */
@Service
@DS("environmentmonitoring")
public class OperationLogServiceImpl implements OperationLogService {

   /**
	 * @Description: 注入OperationLogMapper
	 */
	@Autowired
	private OperationLogMapper operationLogMapper;

	/**
	 * @Author Wei Wei
	 * @Description 新增OperationLog
	 * @Date 2019-09-27
	 * @Params [OperationLog]
	 * @return void
	 **/
	@Override
	@Transactional
	public void insertOperationLog(OperationLog operationLog) {
		operationLogMapper.insertOperationLog(operationLog);
	}

}

