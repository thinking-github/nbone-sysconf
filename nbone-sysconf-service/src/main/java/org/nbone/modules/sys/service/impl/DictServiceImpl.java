package org.nbone.modules.sys.service.impl;

import org.nbone.framework.spring.dao.namedparam.NamedJdbcDao;
import org.nbone.modules.sys.entity.Dict;
import org.nbone.modules.sys.service.DictService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.nbone.persistence.SqlConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceDomain<Dict, Integer> implements DictService {

    public final static String[] ENUM_FIELDS = {"value", "label", "description", "parentId", "type"};

    @Resource
    private NamedJdbcDao namedJdbcDao;

    public DictServiceImpl() {
        super(false);
    }


    @Override
    public List<Dict> getForList(Dict dict, String[] fieldNames, boolean parent) {
        SqlConfig sqlConfig = new SqlConfig(-1).fieldNames(fieldNames);
        List<Dict> dicts = namedJdbcDao.getForList(dict, sqlConfig);
        if (parent) {
            namedJdbcDao.getParentName(dicts, Dict.class, "id", "label", dict.getTableName());
        }
        return dicts;
    }

    @Override
    public Page<Dict> getForPage(Dict dict, int pageNum, int pageSize, boolean parent) {
        SqlConfig sqlConfig = new SqlConfig(-1);
        Page<Dict> page = namedJdbcDao.getForPage(dict, sqlConfig, pageNum, pageSize);
        if (parent) {
            namedJdbcDao.getParentName(page.getContent(), Dict.class, "id", "label", dict.getTableName());
        }
        return page;
    }
}
