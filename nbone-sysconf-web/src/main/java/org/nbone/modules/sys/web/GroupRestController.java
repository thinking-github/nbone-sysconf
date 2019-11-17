package org.nbone.modules.sys.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-17
 */
@Api(tags = {"分组管理"})
@RestController
@RequestMapping(value = {"sys/group"})
public class GroupRestController extends BaseController {

}
