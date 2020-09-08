package org.nbone.modules.sys.service.impl;

import org.nbone.modules.sys.entity.Device;
import org.nbone.modules.sys.service.DeviceService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.springframework.stereotype.Service;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-05-27
 */
@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceDomain<Device, Long> implements DeviceService {


}
