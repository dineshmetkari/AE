package com.tfevaluator.TrueFalseEvaluatorServices.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//This Configuration annotation tells Spring that it is the config file for the project
@EnableSwagger2
//This ensures spring to use Swagger2 configuration
public class SwaggerConfig {
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.tfevaluator.TrueFalseEvaluatorServices"))
				.paths(PathSelectors.any()).build();
	}
}