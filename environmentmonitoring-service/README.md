本项目主要技术包括: springboot-2.1.6, dynamic datasource-2.5.6, druid连接池, 
                 mybatisplus-3.0.6, mybatisplus generator, JWT-0.9.1, 
                 swagger2, docker-maven-plugin

一、代码层的结构

根目录：com.upsoft

1.实体类(domain)置于com.upsoft.environmentmonitoring.domain
    1) com.upsoft.environmentmonitoring.domain.po（Persistant Object）：与数据库表结构一一对应，通过DAO层向上传输数据源对象。
    2) com.upsoft.environmentmonitoring.domain.vo（View Object）：显示层对象，通常是Controller向模板渲染引擎层传输的对象。
    3) com.upsoft.environmentmonitoring.domain.qo（Query Object）：数据查询对象，各层接收上层的查询请求。
    注意超过2个参数的查询要封装，禁止使用Map类来传输。少于2个的可以直接用常规对象封装或接收。

2.数据访问层(Dao)置于com.upsoft.environmentmonitoring.dao

3.数据服务层(Service)置于com.upsoft.environmentmonitoring.service,数据服务的实现接口
    1) com.upsoft.environmentmonitoring.service.impl: 接口实现类包，类名通常是XxxServiceImpl

4.前端控制器(Controller)置于com.upsoft.environmentmonitoring.controller

5.工具类(utils)置于com.upsoft.environmentmonitoring.utils

6.常量接口类(constant)置于com.upsoft.environmentmonitoring.constant

7.配置信息类(config)置于com.upsoft.environmentmonitoring.config

8.自定义异常信息类(exception)置于com.upsoft.environmentmonitoring.exception

9.各种拦截器类(Handler和Interceptor)置于com.upsoft.environmentmonitoring.handler

10.Mybtis-Plus代码生成器相关代码置于com.upsoft.environmentmonitoring.generator

11.自定义注解相关的代码置于com.upsoft.environmentmonitoring.annotation

二、资源文件的结构

根目录:src/main/resources

1.配置文件(.properties/.json/.yml等)置于config文件夹下

2.数据库创建SQL文件置于db文件夹下

3.mybatis的sql映射文件置于mybatis/mapper文件下
    
4.国际化(i18n))置于i18n文件夹下

5.代码自动生成Freemarker模板文件置于resources/templates文件夹下

三、测试包Test的结构

1.测试类的结构应与Java文件结构一致

四、swagger2注解说明

@Api：用在请求的类上，表示对类的说明
	tags="说明该类的作用，可以在UI界面上看到的注解"
	value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
	
	eg:
	    @Api(tags="APP用户注册Controller")

@ApiOperation：用在请求的方法上，说明方法的用途、作用
	value="说明方法的用途、作用"
	notes="方法的备注说明"
	
	eg:
	    @ApiOperation(value="用户注册",notes="手机号、密码都是必输项，年龄随边填，但必须是数字")

@ApiImplicitParams：用在请求的方法上，表示一组参数说明
	@ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
	    name：参数名
	    value：参数的汉字说明、解释
	    required：参数是否必须传
	    paramType：参数放在哪个地方
	        · header --> 请求参数的获取：@RequestHeader
	        · query --> 请求参数的获取：@RequestParam
	        · path（用于restful接口）--> 请求参数的获取：@PathVariable
	        · body（不常用）
	        · form（不常用）	   
	    dataType：参数类型，默认String，其它值dataType="Integer"	   
	    defaultValue：参数的默认值
	    
    eg:
        @ApiImplicitParams({
        @ApiImplicitParam(name="mobile",value="手机号",required=true,paramType="form"),
        @ApiImplicitParam(name="password",value="密码",required=true,paramType="form"),
        @ApiImplicitParam(name="age",value="年龄",required=true,paramType="form",dataType="Integer")
       })
         
@ApiResponses：用在请求的方法上，表示一组响应
	@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
	    code：数字，例如400
	    message：信息，例如"请求参数没填好"
	    response：抛出异常的类
	  
	eg:
	    @ApiResponses({
       	@ApiResponse(code=400,message="请求参数没填好"),
       	@ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
       })
	    
@ApiModel：用于响应类上，表示一个返回响应数据的信息
			（这种一般用在post创建的时候，使用@RequestBody这样的场景，
			请求参数无法使用@ApiImplicitParam注解进行描述的时候）
	@ApiModelProperty：用在属性上，描述响应类的属性

    eg:
        import io.swagger.annotations.ApiModel;
        import io.swagger.annotations.ApiModelProperty;
        
        import java.io.Serializable;
        
        @ApiModel(description= "返回响应数据")
        public class RestMessage implements Serializable{
        
        	@ApiModelProperty(value = "是否成功")
        	private boolean success=true;
        	@ApiModelProperty(value = "返回对象")
        	private Object data;
        	@ApiModelProperty(value = "错误编号")
        	private Integer errCode;
        	@ApiModelProperty(value = "错误信息")
        	private String message;
        	
        	/* getter/setter */
        }

