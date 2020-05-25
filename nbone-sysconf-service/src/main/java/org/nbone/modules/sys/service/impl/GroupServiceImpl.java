package org.nbone.modules.sys.service.impl;

import org.nbone.framework.spring.dao.namedparam.NamedJdbcDao;
import org.nbone.modules.sys.entity.Group;
import org.nbone.modules.sys.service.GroupService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.nbone.persistence.SqlConfig;
import org.nbone.persistence.enums.QueryType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-17
 */
@Service("groupService")
public class GroupServiceImpl extends BaseServiceDomain<Group, Integer> implements GroupService {

    public final static String[] ENUM_FIELDS = {"id", "name", "description", "parentId", "type"};
    @Resource
    private NamedJdbcDao namedJdbcDao;

    public GroupServiceImpl() {
        super(false);
    }

    @Override
    public boolean checkDuplication(Group group, String[] conditionFields) {
        if (group.getName() != null) {
            SqlConfig sqlConfig = SqlConfig.builder(-1).conditionFields(conditionFields);
            long count = namedJdbcDao.count(group, sqlConfig);
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Group> getForList(Group group, String[] fieldNames, boolean parent) {
        SqlConfig sqlConfig = new SqlConfig(-1).fieldNames(fieldNames);
        List<Group> groups = namedJdbcDao.getForList(group, sqlConfig);
        if (parent) {
            namedJdbcDao.getParentName(groups, Group.class, "id", "name", group.getTableName());
        }
        return groups;
    }

    @Override
    public Page<Group> getForPage(Group group, int pageNum, int pageSize, boolean parent) {
        SqlConfig sqlConfig = new SqlConfig(-1)
                .addOperation("name", QueryType.LIKE.name())
                .orderBy(group.getOrderBy());
        Page<Group> page = namedJdbcDao.getForPage(group, sqlConfig, pageNum, pageSize);
        if (parent) {
            namedJdbcDao.getParentName(page.getContent(), Group.class, "id", "name", group.getTableName());
        }
        return page;
    }
}
