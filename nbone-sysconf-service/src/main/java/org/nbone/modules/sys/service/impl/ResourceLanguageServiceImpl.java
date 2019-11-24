package org.nbone.modules.sys.service.impl;

import org.nbone.modules.sys.entity.ResourceLanguage;
import org.nbone.modules.sys.service.ResourceLanguageService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.springframework.stereotype.Service;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-24
 */
@Service("resourceLanguageService")
public class ResourceLanguageServiceImpl extends BaseServiceDomain<ResourceLanguage, Integer>
        implements ResourceLanguageService {

    public ResourceLanguageServiceImpl() {
        super(false);
    }
}
