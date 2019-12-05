package com.upsoft.environmentmonitoring.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.upsoft.environmentmonitoring.domain.po.OperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName OperationLogMapper
 * @Description 操作日志表,  在insert后执行;  在update前后都要执行;  在delete前执行 Mapper 接口
 * @Author Wei Wei
 * @Date 2019-09-27
 * @Version 1.0
 */
@Repository
@DS("environmentmonitoring")
public interface OperationLogMapper extends BaseMapper<OperationLog> {

   /**
	 * @Author Wei Wei
	 * @Description 新增OperationLog
	 * @Date 2019-09-27
	 * @Params [OperationLog]
	 * @return void
	 **/
	void insertOperationLog(OperationLog operationLog);
}
