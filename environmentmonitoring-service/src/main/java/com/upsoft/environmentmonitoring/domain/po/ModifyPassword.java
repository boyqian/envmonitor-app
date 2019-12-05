package com.upsoft.environmentmonitoring.domain.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName ModifyPassword
 * @Description 修改密码接收类
 * @Author Wei Wei
 * @Date 2019-11-26
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class ModifyPassword {

	 /**
     * @Description: 旧密码
     */
	private String oldPassword;
	
	 /**
     * @Description: 新密码
     */
	private String newPassword;
}
