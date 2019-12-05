package com.upsoft.environmentmonitoring.config;

import com.upsoft.environmentmonitoring.handler.TokenAuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName WebMvcConfig
 * @Description Springboot WebMvcConfig自定义配置
 * @Author Wei Wei
 * @Date 2018/12/14 8:37
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * @Author weiwei
	 * @Description 将swagger-ui的Handler配置至spring-boot环境
	 * @Date 8:41 2018/12/14
	 * @Params [ResourceHandlerRegistry registry]
	 * @return void
	 **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/**
	 * @Author weiwei
	 * @Description Cors跨域全局配置
	 * @Date 09:41 2019/01/02
	 * @Params [CorsRegistry registry]
	 * @return void
	 **/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT")
				.maxAge(0);
	}

	/**
	 * @Author weiwei
	 * @Description 注册Token验证拦截器
	 * @Date 14:17 2019/9/27
	 * @Params [registry]
	 * @return void
	 **/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenAuthenticationInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/login")
				.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
	}

}
