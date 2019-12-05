package com.upsoft.environmentmonitoring.config;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisPlusConfig
 * @Description mybatis-plus分页配置类
 * @Author weiwei
 * @Date 2018/12/7 13:25
 * @Version 1.0
 */

@Configuration
public class MyBatisPlusConfig {

	/**
	 * @Author weiwei
	 * @Description mybatis-plus分页插件
	 * @Date 13:27 2018/12/7
	 * @Params []
	 * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
	 **/
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}
}
