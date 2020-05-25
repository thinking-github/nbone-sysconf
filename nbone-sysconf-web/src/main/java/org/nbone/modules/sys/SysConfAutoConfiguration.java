package org.nbone.modules.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 *
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-16
 */
@Configuration
@ComponentScan(basePackageClasses = SysConfAutoConfiguration.class)
public class SysConfAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SysConfAutoConfiguration.class);

    @PostConstruct
    public void initialize(){
        logger.info("SysConfAutoConfiguration initialize.");

    }

}
