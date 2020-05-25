package org.nbone.modules.sys.service;

import org.nbone.modules.sys.entity.Dict;
import org.nbone.mvc.service.SuperService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
public interface DictService extends SuperService<Dict, Integer> {


    /**
     * 查询字典列表
     *
     * @param dict
     * @param parent 是否查询上级
     * @return
     */
    public List<Dict> getForList(Dict dict, String[] fieldNames, boolean parent);

    /**
     * 查询字典分页
     *
     * @param object
     * @param pageNum
     * @param pageSize
     * @param parent
     * @return
     */
    public Page<Dict> getForPage(Dict object, int pageNum, int pageSize, boolean parent);
}
