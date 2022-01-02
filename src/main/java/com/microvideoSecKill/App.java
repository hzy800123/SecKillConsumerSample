package com.microvideoSecKill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableAutoConfiguration
@EnableOpenApi
@MapperScan("com.microvideoSecKill.dao")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
