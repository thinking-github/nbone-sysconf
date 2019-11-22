package org.nbone.modules.sys.config;

import org.nbone.framework.spring.dao.config.JdbcComponentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.support.RequestHandledListener;
import org.springframework.web.servlet.mvc.method.annotation.ResultResponseBodyAdvice;

import javax.annotation.PostConstruct;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@ConditionalOnMissingBean(value = JdbcComponentConfig.class)
@Configuration("org.nbone.sysconf.nboneConfig")
@Import({JdbcComponentConfig.class, RequestHandledListener.class, ResultResponseBodyAdvice.class})
public class NboneConfig {

    @Autowired
    private JdbcComponentConfig jdbcConfig;

    @PostConstruct
    public void init() {
        jdbcConfig.setShowSql(true);
        jdbcConfig.setMybatisMapperId("BaseResultMap");
    }
}
