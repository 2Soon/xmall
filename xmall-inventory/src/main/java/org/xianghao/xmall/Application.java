package org.xianghao.xmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.xianghao.xmall.common.config.DruidDataSourceConfig;
import org.xianghao.xmall.inventory.mq.MessageService;

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
@EnableBinding(MessageService.class)
@Import(DruidDataSourceConfig.class)
public class Application {
	
	public static void main(String[] args) { 
		SpringApplication.run(Application.class, args);
	}
	
}
