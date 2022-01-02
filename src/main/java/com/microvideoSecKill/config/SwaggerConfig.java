package com.microvideoSecKill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pumbaa on 2021-10-06.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.microvideoSecKill.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //basic info
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("HMV Backend SecKill API")
                .description("API Description")
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
