package com.upsoft.environmentmonitoring.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CodeGenerator
 * @Description 代码生成器
 * @Author Administrator
 * @Date 2019/4/3
 * @Version 1.0
 */
public class CodeGenerator {

	public void generate(){
		// 通过yml配置文件获取相关配置属性
		Yaml yaml = new Yaml();
		InputStream in = CodeGenerator.class.getClassLoader().getResourceAsStream("config/codeGenerator.yml");
		Map<String, Object> map = yaml.loadAs(in, Map.class);
		String outputDir = map.get("output-dir").toString();
		String author = map.get("author").toString();
		String url = map.get("url").toString();
		String driverName = map.get("driver-name").toString();
		String username = map.get("username").toString();
		String password = map.get("password").toString();
		String parentPackage = map.get("parent-package").toString();
		String entityPackage = map.get("entity-package").toString();
		String daoPackage = map.get("dao-package").toString();
		String servicePackage = map.get("service-package").toString();
		String serviceImplPackage = map.get("serviceImpl-package").toString();
		String controllerPackage = map.get("controller").toString();
		String[] tables = map.get("tables").toString().split(",");
		String[] tablePrefix = map.get("table-prefix").toString().split(",");
		String templatePath = map.get("template-path").toString();
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		// 获取当前项目路径
		String projectPath = System.getProperty("user.dir");
		// 设置自动代码生成的输出文件夹
		gc.setOutputDir(projectPath + outputDir);
		// 设置作者
		gc.setAuthor(author);
		// 设置生成完成后是否打开输出目录
		gc.setOpen(false);
		// 设置实体属性Swagger2注解
		// gc.setSwagger2(true);
		// 设置Mapper文件生成BaseResultMap
		gc.setBaseResultMap(true);
		// 设置service名称
		gc.setServiceName("%sService");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(url);
		// dsc.setSchemaName("public");
		dsc.setDriverName(driverName);
		dsc.setUsername(username);
		dsc.setPassword(password);
		mpg.setDataSource(dsc);
		// 包配置
		PackageConfig pc = new PackageConfig();
		// 设置父包名,如果为空,则下面子包名必须写全部,否则就只需写子包名
		pc.setParent(parentPackage);
		// 设置Entity包名
		pc.setEntity(entityPackage);
		// 设置Mapper包名
		pc.setMapper(daoPackage);
		// 设置service包名
		pc.setService(servicePackage);
		// 设置service实现类包名
		pc.setServiceImpl(serviceImplPackage);
		// 设置controller包名
		pc.setController(controllerPackage);
		mpg.setPackageInfo(pc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// 表名生成策略,生成Entity时将表名和列名的下划线转换成驼峰
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// 设置是否为lombok模式的Entity
		strategy.setEntityLombokModel(true);
		// 设置生成@RestController控制器
		strategy.setRestControllerStyle(true);
		// 设置需要自动生成代码的表名
		strategy.setInclude(tables);
		// 设置controller mapping映射驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		// 设置表前缀
		strategy.setTablePrefix(tablePrefix);
		mpg.setStrategy(strategy);
		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置Mapper XML输出路径
		focList.add(new FileOutConfig("templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件路径
				return projectPath + "/src/main/resources/mybatis/mapper/" + tableInfo.getMapperName() + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		// 配置自定义模板
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setEntity(templatePath + "entity.java");
		templateConfig.setMapper(templatePath + "mapper.java");
		templateConfig.setService(templatePath + "service.java");
		templateConfig.setServiceImpl(templatePath + "serviceImpl.java");
		templateConfig.setController(templatePath + "controller.java");
//		templateConfig.setController(null);
//		templateConfig.setServiceImpl(null);
//		templateConfig.setService(null);
//		templateConfig.setMapper(null);
//		templateConfig.setEntity(null);
		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);
		// 如果模板引擎是freemarker
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

	public static void main(String[] args) {
		CodeGenerator codeGenerator = new CodeGenerator();
		codeGenerator.generate();
	}
}
