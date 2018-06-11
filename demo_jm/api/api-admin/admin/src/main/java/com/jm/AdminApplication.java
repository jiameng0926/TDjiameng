package com.jm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.jm.server")
public class AdminApplication {
	private static final Logger logger = LoggerFactory.getLogger(AdminApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		logger.info("启动成功!");
	}
}
