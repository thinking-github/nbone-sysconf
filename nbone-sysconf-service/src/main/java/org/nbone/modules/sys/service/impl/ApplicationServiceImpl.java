package org.nbone.modules.sys.service.impl;

import org.nbone.framework.spring.dao.namedparam.NamedJdbcDao;
import org.nbone.modules.sys.entity.Application;
import org.nbone.modules.sys.service.ApplicationService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author thinking
 * @version 1.0
 * @since 2020-03-05
 */
@Service("applicationService")
public class ApplicationServiceImpl extends BaseServiceDomain<Application, Integer>
        implements ApplicationService {

    @Resource
    private NamedJdbcDao namedJdbcDao;


    public ApplicationServiceImpl() {
        super(false);
    }


}
