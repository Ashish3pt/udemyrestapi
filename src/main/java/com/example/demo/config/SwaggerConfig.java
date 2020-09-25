package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.paths(PathSelectors.ant("/users/**"))
				.build();
	}
	
	//Swagger Metadata: http://localhost:8080/v2/api-docs
	//Swagger Ui : http://localhost:8080/swagger-ui/
	
	private ApiInfo getApiInfo()
	{
		return new ApiInfoBuilder()
				.title("User-Order Management System")
				.description("This page lists all API's of User Management")
				.version("2.0")
				.contact(new Contact("Ashish Patil","https://google.com/images/","a@x.com"))
				.license("License 2.0")
				.licenseUrl("https://opensource/license.html/")
				.build();
	}
}
