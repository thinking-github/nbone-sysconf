package org.nbone.modules.sys.service.impl;

import org.nbone.framework.spring.dao.namedparam.NamedJdbcDao;
import org.nbone.modules.sys.entity.Configuration;
import org.nbone.modules.sys.service.ConfigurationService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.nbone.persistence.SqlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-29
 */
@Service("configurationService")
public class ConfigurationServiceImpl extends BaseServiceDomain<Configuration, Integer>
        implements ConfigurationService {

    private Map<String, Map<String, Configuration>> configuration = new HashMap<String, Map<String, Configuration>>();

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    @Resource
    private NamedJdbcDao namedJdbcDao;


    public ConfigurationServiceImpl() {
        super(false);
    }

    @Override
    public Map<String, Configuration> refresh(String appId) {
        Assert.notNull(appId, "appId must not be null.");
        Configuration query = new Configuration(appId);
        SqlConfig sqlConfig = SqlConfig.builder(-1).mapKey("name");
        Map<String, Configuration> appConfiguration = namedJdbcDao.getMapWithMapKey(query, sqlConfig);
        configuration.put(appId, appConfiguration);
        return appConfiguration;
    }

    @Override
    public Configuration refresh(String appId, String name) {
        Assert.notNull(appId, "appId must not be null.");
        Assert.notNull(name, "configuration name must not be null.");
        Configuration query = new Configuration(appId, name);
        Configuration configuration = namedJdbcDao.getOne(query,null);
        Map<String, Configuration> appConfiguration = this.configuration.get(appId);
        if (appConfiguration != null) {
            appConfiguration.put(name, configuration);
        }
        return configuration;
    }

    @Override
    public Map<String, Configuration> getConfiguration(String appId) {
        Assert.notNull(appId, "appId must not be null.");
        Map<String, Configuration> appConfiguration = configuration.get(appId);
        if (appConfiguration == null) {
            appConfiguration = refresh(appId);
        }
        return appConfiguration;
    }

    @Override
    public String getString(String appId, String name) {
        Map<String, Configuration> appConfiguration = getConfiguration(appId);
        if (appConfiguration == null) {
            return null;
        }
        Configuration configuration = appConfiguration.get(name);
        if (configuration == null) {
            logger.error("appId {} configuration parameter {} not exist.", appId, name);
            return null;
        }
        return configuration.getValue();
    }

    @Override
    public int getInt(String appId, String name) {
        String value = getString(appId, name);
        if (StringUtils.isEmpty(value)) {
            return -1;
        }
        return Integer.valueOf(value);
    }

    @Override
    public long getLong(String appId, String name) {
        String value = getString(appId, name);
        if (StringUtils.isEmpty(value)) {
            return -1;
        }
        return Long.valueOf(value);
    }

    @Override
    public boolean getBoolean(String appId, String name) {
        String value = getString(appId, name);
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return Boolean.valueOf(value);
    }


}
