package com.upsoft.environmentmonitoring;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableTransactionManagement
@MapperScan("com.upsoft.environmentmonitoring.dao")
public class EnvironmentmonitoringApplication {

	public static void main(String[] args) {
		// 配置加解密跟秘钥，与配置文件的密文分开
		// System.setProperty("jasypt.encryptor.password", "environmentmonitoring");
		SpringApplication.run(EnvironmentmonitoringApplication.class, args);
	}
}
