package org.nbone.modules.sys.service.impl;

import org.nbone.framework.spring.dao.namedparam.NamedJdbcDao;
import org.nbone.modules.sys.entity.DictInfo;
import org.nbone.modules.sys.entity.DictValue;
import org.nbone.modules.sys.service.DictInfoService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.nbone.persistence.SqlConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2020-03-05
 */
public class DictInfoServiceImpl extends BaseServiceDomain<DictInfo, Integer>
        implements DictInfoService {

    @Resource
    private NamedJdbcDao namedJdbcDao;

    public DictInfoServiceImpl() {
        super(false);
    }

    @Transactional
    @Override
    public void addDictInfo(DictInfo dictInfo) {
        List<DictValue> dictValues = dictInfo.getValues();
        if (CollectionUtils.isEmpty(dictValues)) {
            namedJdbcDao.insert(dictInfo);
            return;
        }

        // Include  dictValues 
        Serializable id = namedJdbcDao.save(dictInfo);
        for (DictValue dictValue : dictValues) {
            dictValue.setDictId((Integer) id);
        }
        addDictValues(dictValues);
    }

    @Override
    public void updateDictInfo(DictInfo dictInfo) {
        namedJdbcDao.updateSelective(dictInfo);
        addOrUpdateDictValues(dictInfo.getValues());
    }


    @Override
    public void addDictValue(DictValue dictValue) {
        namedJdbcDao.insert(dictValue);
    }

    @Override
    public void addDictValues(List<DictValue> dictValues) {
        namedJdbcDao.batchInsert(dictValues, false);
    }

    @Override
    public void updateDictValue(DictValue dictValue) {
        namedJdbcDao.updateSelective(dictValue);
    }

    @Override
    public void updateDictValues(List<DictValue> dictValues) {
        namedJdbcDao.batchUpdate(dictValues);
    }

    @Override
    public void addOrUpdateDictValues(List<DictValue> dictValues) {
        if (CollectionUtils.isEmpty(dictValues)) {
            return;
        }
        List<DictValue> add = null;
        List<DictValue> update = null;
        for (DictValue dictValue : dictValues) {
            if (dictValue.getId() == null) {
                if (add == null) {
                    add = new ArrayList<DictValue>();
                }
                add.add(dictValue);
            } else {
                if (update == null) {
                    update = new ArrayList<DictValue>();
                }
                update.add(dictValue);
            }
        }

        if (!CollectionUtils.isEmpty(add)) {
            namedJdbcDao.batchInsert(add, false);
        }
        if (!CollectionUtils.isEmpty(update)) {
            namedJdbcDao.batchUpdate(add);
        }
    }

    @Override
    public List<DictValue> getDictValues(Integer dictId, String tableName) {
        return getDictValues(dictId, null, tableName);
    }

    @Override
    public List<DictValue> getDictValues(Integer dictId, String[] fieldNames, String tableName) {
        SqlConfig sqlConfig = SqlConfig.builder(-1)
                .withEntityClass(DictValue.class)
                .tableName(tableName)
                .fieldNames(fieldNames)
                .addOperation("dictId", "=", dictId);
        List<DictValue> dictValues = namedJdbcDao.getForList((Object) null, sqlConfig);
        return dictValues;
    }
}
