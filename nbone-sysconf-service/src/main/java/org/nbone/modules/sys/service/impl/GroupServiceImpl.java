package org.nbone.modules.sys.service.impl;

import org.nbone.modules.sys.entity.Group;
import org.nbone.modules.sys.service.GroupService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.springframework.stereotype.Service;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-17
 */
@Service("groupService")
public class GroupServiceImpl extends BaseServiceDomain<Group, Integer> implements GroupService {

    public GroupServiceImpl() {
        super(false);
    }

}
