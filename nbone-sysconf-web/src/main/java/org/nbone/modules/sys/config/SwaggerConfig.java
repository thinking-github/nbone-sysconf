package org.nbone.modules.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration("org.nbone.sysconf.swaggerConfig")
@EnableSwagger2
@Profile({"dev", "test"})
public class SwaggerConfig {

    @Bean("nbone-sysconf")
    public Docket createRestApi() {
        List<ResponseMessage> responseMessages = responseMessages();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("nbone-sysconf")
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("org.nbone.modules.sys.web"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("nbone-sysconf").description("nbone-sysconf").version("1.0").build();
    }

    private List<ResponseMessage> responseMessages() {
        List<ResponseMessage> responses = new ArrayList<>();
        responses.add(new ResponseMessageBuilder().code(404).message("找不到资源").responseModel(new ModelRef("ApiResponse")).build());
        responses.add(new ResponseMessageBuilder().code(409).message("业务逻辑异常").responseModel(new ModelRef("ApiResponse")).build());
        responses.add(new ResponseMessageBuilder().code(422).message("参数校验异常").responseModel(new ModelRef("ApiResponse")).build());
        return Collections.emptyList();
    }

}
