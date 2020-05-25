package org.nbone.modules.sys.service.impl;

import org.nbone.framework.spring.dao.namedparam.NamedJdbcDao;
import org.nbone.modules.sys.entity.ResourceLanguage;
import org.nbone.modules.sys.service.ResourceLanguageService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.nbone.persistence.SqlConfig;
import org.nbone.persistence.SqlOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-24
 */
@Service("resourceLanguageService")
public class ResourceLanguageServiceImpl extends BaseServiceDomain<ResourceLanguage, Integer>
        implements ResourceLanguageService {

    @Resource
    private NamedJdbcDao namedJdbcDao;

    public ResourceLanguageServiceImpl() {
        super(false);
    }




    @Override
    public void batchInsert(List<? extends ResourceLanguage> resourceLanguages) {
        super.batchInsert(resourceLanguages,null,false);
    }

    @Transactional
    @Override
    public void deleteAndAddLanguage(List<? extends ResourceLanguage> resourceLanguages, String tableName) {
        if (CollectionUtils.isEmpty(resourceLanguages)) {
            return;
        }
        Set<Integer> resourceIds = new HashSet<Integer>();
        for (ResourceLanguage resourceLanguage : resourceLanguages) {
            resourceIds.add(resourceLanguage.getResourceId());
        }

        // String in = SqlUtils.in("", "resource_id", Integer.class, resourceIds, true).toString();
        SqlConfig sqlConfig = SqlConfig.builder(-1)
                .withEntityClass(ResourceLanguage.class)
                .tableName(tableName)
                .firstCondition(SqlOperation.in("resourceId", resourceIds))
                .usedExtField(false);
        namedJdbcDao.delete(sqlConfig);

      /*  ResourceLanguage resourceLanguage = new ResourceLanguage(1,"EN");
        resourceLanguage.setTableName(tableName);
        namedJdbcDao.deleteByEntity(resourceLanguage);*/

        namedJdbcDao.batchInsert(resourceLanguages, false);
    }

    @Override
    public void addOrUpdateLanguage(List<? extends ResourceLanguage> resourceLanguages, String tableName) {
        if (CollectionUtils.isEmpty(resourceLanguages)) {
            return;
        }
        List<ResourceLanguage> updates = null;
        List<ResourceLanguage> adds = new ArrayList<ResourceLanguage>();
        for (ResourceLanguage resourceLanguage : resourceLanguages) {
            if(resourceLanguage.getId() == null){
                adds.add(resourceLanguage);
            }else {
                if(updates == null){
                    updates = new ArrayList<ResourceLanguage>();
                }
                updates.add(resourceLanguage);
            }
        }

        if (!CollectionUtils.isEmpty(adds)) {
            namedJdbcDao.batchInsert(adds, false);
        }
        if (!CollectionUtils.isEmpty(updates)) {
            namedJdbcDao.batchUpdate(updates);
        }
    }

    @Transactional
    @Override
    public void addOrUpdateLanguageByLangCode(List<? extends ResourceLanguage> resourceLanguages, String tableName) {
        if (CollectionUtils.isEmpty(resourceLanguages)) {
            return;
        }
        Set<Integer> resourceIds = new HashSet<Integer>();
        for (ResourceLanguage resourceLanguage : resourceLanguages) {
            resourceIds.add(resourceLanguage.getResourceId());
        }

        // String in = SqlUtils.in("", "resource_id", Integer.class, resourceIds, true).toString();
        SqlConfig sqlConfig = SqlConfig.builder(-1)
                .withEntityClass(ResourceLanguage.class)
                .tableName(tableName)
                .fieldNames("id","languageCode")
                .firstCondition(SqlOperation.in("resourceId", resourceIds))
                .usedExtField(false);
        List<ResourceLanguage> hasResourceLanguages = namedJdbcDao.getForList((Object) null,sqlConfig);

        List<ResourceLanguage> updates = null;
        List<ResourceLanguage> adds = new ArrayList<ResourceLanguage>();
        for (ResourceLanguage resourceLanguage : resourceLanguages) {
            Integer id = contains(hasResourceLanguages,resourceLanguage);
            if(id == null){
                adds.add(resourceLanguage);
            }else {
                if(updates == null){
                    updates = new ArrayList<ResourceLanguage>();
                }
                resourceLanguage.setId(id);
                updates.add(resourceLanguage);
            }
        }

        if (!CollectionUtils.isEmpty(adds)) {
            namedJdbcDao.batchInsert(adds, false);
        }
        if (!CollectionUtils.isEmpty(updates)) {
            namedJdbcDao.batchUpdate(updates);
        }

    }

    private Integer contains(List<? extends ResourceLanguage> hasResourceLanguages, ResourceLanguage resourceLanguage) {
        if (resourceLanguage == null) {
            return null;
        }
        for (ResourceLanguage hasResourceLanguage : hasResourceLanguages) {
            if (hasResourceLanguage.getLanguageCode().equals(resourceLanguage.getLanguageCode())) {
                return hasResourceLanguage.getId();
            }
        }
        return null;

    }

    @Override
    public List<Map<String, Object>> getLanguages(ResourceLanguage resourceLanguage) {
        SqlConfig sqlConfig = SqlConfig.builder(-1)
                .withMappingClass(Map.class)
                .usedExtField(false);
        List<Map<String, Object>> maps = namedJdbcDao.getForList(resourceLanguage, sqlConfig);
        return maps;
    }
}
