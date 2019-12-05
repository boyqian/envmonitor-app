package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};

/**
 * @ClassName ${table.serviceImplName}
 * @Description ${table.comment!} 服务实现类
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 */
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

   /**
	 * @Description: 注入${table.mapperName}
	 */
	@Autowired
	private ${table.mapperName} ${table.mapperName?uncap_first};

	/**
	 * @Author ${author}
	 * @Description 新增${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return void
	 **/
	@Override
	@Transactional
	public void insert${entity}(${entity} ${entity?uncap_first}) {
		${table.mapperName?uncap_first}.insert${entity}(${entity?uncap_first});
	}

	/**
	 * @Author ${author}
	 * @Description 分页查询${entity}列表
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return IPage<${entity}>
	 **/
	@Override
	public IPage<${entity}> list${entity}ByPage(Page<${entity}> page, ${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.list${entity}ByPage(page, ${entity?uncap_first});
    }

    /**
	 * @Author ${author}
	 * @Description 查询单个${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return ${entity}
	 **/
    @Override
    public ${entity} get${entity}(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.get${entity}(${entity?uncap_first});
    }

   /**
	 * @Author ${author}
	 * @Description 修改${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return void
	 **/
    @Override
    @Transactional
    public void update${entity}(${entity} ${entity?uncap_first}) {
		${table.mapperName?uncap_first}.update${entity}(${entity?uncap_first});
    }

    /**
	 * @Author ${author}
	 * @Description 删除${entity}
	 * @Date ${date}
	 * @Params [${entity}]
	 * @return void
	 **/
    @Override
    @Transactional
    public void delete${entity}(${entity} ${entity?uncap_first}) {
		${table.mapperName?uncap_first}.delete${entity}(${entity?uncap_first});
    }
}

