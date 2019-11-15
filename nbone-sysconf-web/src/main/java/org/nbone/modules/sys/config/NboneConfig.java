package org.nbone.modules.sys.config;

import org.nbone.framework.spring.dao.config.JdbcComponentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Configuration
@Import({JdbcComponentConfig.class})
public class NboneConfig {

    @Autowired
    private JdbcComponentConfig jdbcConfig;

    @PostConstruct
    public void init() {
        jdbcConfig.setShowSql(true);
        jdbcConfig.setMybatisMapperId("BaseResultMap");
    }
}
