package org.nbone.modules.sys.service;

import org.nbone.modules.sys.entity.ResourceLanguage;
import org.nbone.mvc.service.SuperService;

import java.util.List;
import java.util.Map;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-24
 */
public interface ResourceLanguageService extends SuperService<ResourceLanguage, Integer> {


    /**
     * 批量插入
     *
     * @param resourceLanguages
     */
    void batchInsert(List<? extends ResourceLanguage> resourceLanguages);

    /**
     * 删除以前的数据,保存新的数据
     *
     * @param resourceLanguages 资源语言列表
     * @param tableName         表名称 可为空,为空时使用实体映射
     */
    void deleteAndAddLanguage(List<? extends ResourceLanguage> resourceLanguages, String tableName);


    /**
     * 保存新增数据 和更新已经存在的数据 根据id判断 id值为空新增 id值不为空更新
     *
     * @param resourceLanguages 资源语言列表
     * @param tableName         表名称 可为空,为空时使用实体映射
     */
    void addOrUpdateLanguage(List<? extends ResourceLanguage> resourceLanguages, String tableName);


    /**
     * 保存新增数据 和更新已经存在的数据 根据语言代码判断 languageCode不匹配新增 匹配更新
     *
     * @param resourceLanguages 资源语言列表
     * @param tableName         表名称 可为空,为空时使用实体映射
     */
    void addOrUpdateLanguageByLangCode(List<? extends ResourceLanguage> resourceLanguages, String tableName);


    /**
     * 获取多语言列表
     *
     * @param resourceLanguage 查询资源语言条件
     * @return Map
     */
    List<Map<String, Object>> getLanguages(ResourceLanguage resourceLanguage);
}
