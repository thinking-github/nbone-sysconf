package org.nbone.modules.sys.service.impl;

import org.nbone.modules.sys.entity.Dict;
import org.nbone.modules.sys.service.DictService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.springframework.stereotype.Service;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceDomain<Dict, Integer> implements DictService {

    public DictServiceImpl() {
        super(false);
    }

}
