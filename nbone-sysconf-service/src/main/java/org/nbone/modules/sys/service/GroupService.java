package org.nbone.modules.sys.service;

import org.nbone.modules.sys.entity.Group;
import org.nbone.mvc.service.SuperService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-17
 */
public interface GroupService extends SuperService<Group, Integer> {

    /**
     * 检查是否重复
     *
     * @param group           实体对象
     * @param conditionFields 检查条件字段
     * @return true 重复
     */
    public boolean checkDuplication(Group group, String[] conditionFields);
    /**
     * 查询分组列表
     *
     * @param group
     * @param parent 是否查询上级
     * @return
     */
    public List<Group> getForList(Group group,String[] fieldNames, boolean parent);

    /**
     * 查询分组分页
     *
     * @param object
     * @param pageNum
     * @param pageSize
     * @param parent
     * @return
     */
    public Page<Group> getForPage(Group object, int pageNum, int pageSize, boolean parent);


}
