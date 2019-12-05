package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * @ClassName ${table.controllerName}
 * @Description ${entity} Restful API接口
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Validated
@Api(value = "${entity}管理接口")
public class ${table.controllerName} {
</#if>
   /**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Description: 注入${table.serviceName}
	 */
	@Autowired
	private ${table.serviceName} ${table.serviceName?uncap_first};

	/**
	 * @Author ${author}
	 * @Description ${entity}列表
	 * @Date ${date}
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/${entity?uncap_first}")
	@ApiOperation(value = "${entity}列表")
	public BaseResult list${entity}(){
		Page<${entity}> page = new Page();
		IPage<${entity}> ${entity?uncap_first}List = ${table.serviceName?uncap_first}.list${entity}ByPage(page,null);
		return BaseResultUtil.success(${entity?uncap_first}List);
	}

	/**
	 * @Author ${author}
	 * @Description 根据参数查询${entity}列表
	 * @Date ${date}
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping(value = "/${entity?uncap_first}/list${entity}ByParams")
	@ApiOperation(value = "根据参数查询查询${entity}列表")
	public BaseResult list${entity}ByParams(@RequestBody ${entity} ${entity?uncap_first}){
		Page<${entity}> page = new Page();
		IPage<${entity}> ${entity?uncap_first}List = ${table.serviceName?uncap_first}.list${entity}ByPage(page, ${entity?uncap_first});
		return BaseResultUtil.success(${entity?uncap_first}List);
	}

	/**
	 * @Author ${author}
	 * @Description 新增${entity}
	 * @Date ${date}
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/${entity?uncap_first}/add")
	@ApiOperation(value = "新增${entity}")
	public BaseResult add${entity}(@RequestBody ${entity} ${entity?uncap_first}){
        <#list table.fields as field>
        <#if field.keyFlag>
		${entity?uncap_first}.set${field.propertyName?cap_first}(ObjectIdUtil.id());
        </#if>
        </#list>
        ${table.serviceName?uncap_first}.insert${entity}(${entity?uncap_first});
		return BaseResultUtil.success("新增成功");
	}

	/**
	 * @Author ${author}
	 * @Description 修改${entity}
	 * @Date ${date}
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/${entity?uncap_first}/update")
	@ApiOperation(value = "修改${entity}")
	public BaseResult update${entity}(@RequestBody ${entity} ${entity?uncap_first}){
		${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first});
		return BaseResultUtil.success("更新成功");
	}

	/**
	 * @Author ${author}
	 * @Description 删除${entity}
	 * @Date  ${date}
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/${entity?uncap_first}/delete")
	@ApiOperation(value = "删除${entity}")
	public BaseResult delete${entity}(@RequestBody ${entity} ${entity?uncap_first}){
		${table.serviceName?uncap_first}.delete${entity}(${entity?uncap_first});
		return BaseResultUtil.success("删除成功");
	}
}
</#if>
