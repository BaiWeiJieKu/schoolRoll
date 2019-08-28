package com.purple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.purple.controller")).paths(PathSelectors.any()).build();
	}

	private ApiInfo getApiInfo() {
		/*
		 * return new ApiInfo("keepurple博客api文档", "api接口描述", "1.0",
		 * "http://www.keepurple.cn", "keepurple", "", "");
		 */

		return new ApiInfoBuilder().title("学籍管理系统api文档").description("api接口描述")
				.termsOfServiceUrl("http://localhost/swagger-ui.html").version("1.0").build();
	}
}
