package org.xianghao.xmall;

import org.xianghao.xmall.common.config.DruidDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 系统启动类
 * @author xianghao
 *
 */
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients
@Import(DruidDataSourceConfig.class)
public class Application {
	
	public static void main(String[] args) { 
		SpringApplication.run(Application.class, args);
	}
	
}
