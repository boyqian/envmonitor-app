package com.upsoft.environmentmonitoring.service;

import com.upsoft.environmentmonitoring.domain.po.OperationLog;

/**
 * @ClassName OperationLogMapper
 * @Description 操作日志表,  在insert后执行;  在update前后都要执行;  在delete前执行服务接口
 * @Author Wei Wei
 * @Date 2019-09-27
 * @Version 1.0
 */
public interface OperationLogService {

   /**
	 * @Author Wei Wei
	 * @Description 新增OperationLog
	 * @Date 2019-09-27
	 * @Params [OperationLog]
	 * @return void
	 **/
	void insertOperationLog(OperationLog operationLog);

}
