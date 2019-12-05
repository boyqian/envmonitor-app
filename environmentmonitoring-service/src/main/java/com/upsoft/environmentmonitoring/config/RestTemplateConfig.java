package com.upsoft.environmentmonitoring.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RestTemplateConfig
 * @Description 远程调用rest接口
 * @Author Wei Wei
 * @Date 2019/10/29
 * @Version 1.0
 */

@Configuration
public class RestTemplateConfig {

	/**
	 * @Description: 连接超时时间(毫秒)
	 */
	@Value("${resttemplate.connection.timeout}")
	private String connectionTimeout;

	/**
	 * @Description: 信息读取超时时间(毫秒)
	 */
	@Value("${resttemplate.read.timeout}")
	private String readTimeout;

	/**
	 * @Author weiwei
	 * @Description 注册restTemplate服务
	 * @Date 10:37 2019/10/29
	 * @Params []
	 * @return org.springframework.web.client.RestTemplate
	 **/
	@Bean
	public RestTemplate registerTemplate() {
		RestTemplate restTemplate = new RestTemplate(getFactory());
		//这个地方需要配置消息转换器，不然收到消息后转换会出现异常
		restTemplate.setMessageConverters(getConverts());
		return restTemplate;
	}

	/**
	 * @Author weiwei
	 * @Description 初始化请求工厂
	 * @Date 10:38 2019/10/29
	 * @Params []
	 * @return org.springframework.http.client.SimpleClientHttpRequestFactory
	 **/
	private SimpleClientHttpRequestFactory getFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(Integer.parseInt(connectionTimeout));
		factory.setReadTimeout(Integer.parseInt(readTimeout));
		return factory;
	}

	/**
	 * @Author weiwei
	 * @Description 设置数据转换器，这里只设置了String转换器
	 * @Date 10:38 2019/10/29
	 * @Params []
	 * @return java.util.List<org.springframework.http.converter.HttpMessageConverter<?>>
	 **/
	private List<HttpMessageConverter<?>> getConverts() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		// String转换器
		StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
		List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
			//配置text/plain和text/html类型的数据都转成String
			add(new MediaType("text", "plain", Charset.forName("UTF-8")));
			add(MediaType.TEXT_HTML);
		}};
		stringConvert.setSupportedMediaTypes(stringMediaTypes);
		messageConverters.add(stringConvert);
		return messageConverters;
	}
}
