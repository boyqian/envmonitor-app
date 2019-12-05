package com.upsoft.environmentmonitoring.controller;

import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;
import com.upsoft.environmentmonitoring.utils.ObjectIdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.upsoft.environmentmonitoring.domain.po.OperationLog;
import com.upsoft.environmentmonitoring.service.OperationLogService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OperationLogController
 * @Description OperationLog Restful API接口
 * @Author Wei Wei
 * @Date 2019-09-27
 * @Version 1.0
 */
@RestController
@Validated
@Api(value = "OperationLog管理接口")
public class OperationLogController {
   /**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入OperationLogService
	 */
	@Autowired
	private OperationLogService operationLogService;

	/**
	 * @Author Wei Wei
	 * @Description 新增OperationLog
	 * @Date 2019-09-27
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/operationLog/add")
	@ApiOperation(value = "新增OperationLog")
	public BaseResult addOperationLog(@RequestBody OperationLog operationLog){
		operationLog.setOperationLogId(ObjectIdUtil.id());
        operationLogService.insertOperationLog(operationLog);
		return BaseResultUtil.success("新增成功");
	}

}
