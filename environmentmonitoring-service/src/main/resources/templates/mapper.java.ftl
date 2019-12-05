package ${package.Mapper};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.springframework.stereotype.Repository;

/**
 * @ClassName ${table.mapperName}
 * @Description ${table.comment!} Mapper 接口
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 */
@Repository
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

   /**
	 * @Author ${author}
	 * @Description 新增${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return void
	 **/
	void insert${entity}(${entity} ${entity?uncap_first});

	/**
	 * @Author ${author}
	 * @Description 分页查询${entity}列表
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return IPage<${entity}>
	 **/
	IPage<${entity}> list${entity}ByPage(Page<${entity}> page, @Param("${entity?uncap_first}") ${entity} ${entity?uncap_first});

	/**
	 * @Author ${author}
	 * @Description 查询单个${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return ${entity}
	 **/
    ${entity} get${entity}(${entity} ${entity?uncap_first});

	/**
	 * @Author ${author}
	 * @Description 修改${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return void
	 **/
	void update${entity}(${entity} ${entity?uncap_first});

	/**
	 * @Author ${author}
	 * @Description 删除${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return void
	 **/
	void delete${entity}(${entity} ${entity?uncap_first});
}
</#if>
