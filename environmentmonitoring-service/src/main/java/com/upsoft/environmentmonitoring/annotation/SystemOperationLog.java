package com.upsoft.environmentmonitoring.annotation;

import java.lang.annotation.*;

/**
 * @ClassName SystemOperationLog
 * @Description 自定义操作日志注解
 * @Author Administrator
 * @Date 2019/9/27
 * @Version 1.0
 */

@Target({ElementType.PARAMETER, ElementType.METHOD}) // 作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
@Documented // 表明这个注解应该被 javadoc工具记录
public @interface SystemOperationLog {
	// 操作描述信息
	String description() default "";
	// 操作类型(查询,新增, 修改, 删除)
	String operationType() default "";
}
