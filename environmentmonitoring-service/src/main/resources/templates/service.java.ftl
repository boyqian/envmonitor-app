package ${package.Service};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @ClassName ${table.mapperName}
 * @Description ${table.comment!}服务接口
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 */
public interface ${table.serviceName} {

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
	IPage<${entity}> list${entity}ByPage(Page<${entity}> page, ${entity} ${entity?uncap_first});

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
