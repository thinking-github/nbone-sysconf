package org.nbone.modules.sys;

import org.nbone.framework.spring.web.method.annotation.ResultResponseBodyMethodProcessor;
import org.nbone.mvc.rest.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-14
 */
@SpringBootApplication
public class BootApplication  extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        ApiResponse.initialize(1,0);
        SpringApplication.run(BootApplication.class, args);
    }



    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new ResultResponseBodyMethodProcessor());
    }
}
