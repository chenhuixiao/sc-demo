package com.example.scexcel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 * swagger页面访问地址 http://ip:port/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	/**
	 * 配置Docket的Bean，包括api的基本信息( apiInfo()函数 )，需要显示的接口(apis)，需要过滤的路径(paths)
	 *
	 * @return
	 */
	@Bean
	public Docket creatRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				//api的基本信息
				.apiInfo(apiInfo())
				.select()
				//需要显示的接口
				.apis(RequestHandlerSelectors.basePackage("com.example.scexcel.controller"))
				//需要过滤的路径
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * api的基本信息
	 *
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("sc-demo1 服务接口说明文档")
				//版本号
				.version("0.0.1-SNAPSHOT")
				//接口文档描述
				.description("API文档描述")
				.build();
	}

}
