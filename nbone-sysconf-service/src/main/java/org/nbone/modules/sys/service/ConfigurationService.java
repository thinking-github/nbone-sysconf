package org.nbone.modules.sys.service;

import org.nbone.modules.sys.entity.Configuration;
import org.nbone.mvc.service.SuperService;

import java.util.Map;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-29
 */
public interface ConfigurationService extends SuperService<Configuration, Integer> {


    /**
     * 刷新最新的配置
     *
     * @param appId
     * @return
     */
    Map<String, Configuration> refresh(String appId);


    /**
     * 刷新最新的配置
     *
     * @param appId
     * @param name
     * @return
     */
    Configuration refresh(String appId, String name);

    /**
     * 获取appId的全部配置信息
     *
     * @param appId
     * @return
     */
    Map<String, Configuration> getConfiguration(String appId);

    /**
     * 获取appId的 name 配置信息
     *
     * @param appId
     * @param name
     * @return
     */
    String getString(String appId, String name);

    /**
     * 获取appId的 name 配置信息
     *
     * @param appId
     * @param name
     * @return
     */
    int getInt(String appId, String name);

    /**
     * 获取appId的 name 配置信息
     *
     * @param appId
     * @param name
     * @return
     */
    long getLong(String appId, String name);

    /**
     * 获取appId的 name 配置信息
     *
     * @param appId
     * @param name
     * @return
     */
    boolean getBoolean(String appId, String name);
}
