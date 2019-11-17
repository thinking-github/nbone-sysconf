package org.nbone.modules.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration("org.nbone.sysconf.swaggerConfig")
@EnableSwagger2
@Profile({ "dev", "test" })
public class SwaggerConfig {

	@Bean("nbone-sysconf")
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("nbone-sysconf")
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("org.nbone.modules.sys.web"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("nbone-sysconf").description("nbone-sysconf").version("1.0").build();
	}

}
