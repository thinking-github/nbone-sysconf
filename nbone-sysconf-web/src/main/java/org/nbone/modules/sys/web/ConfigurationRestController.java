package org.nbone.modules.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.modules.sys.entity.Configuration;
import org.nbone.modules.sys.service.ConfigurationService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-29
 */
@Api(tags = {"系统配置管理"})
@RestController
@RequestMapping(value = {"sys/configuration"})
public class ConfigurationRestController {

    @Resource
    private ConfigurationService configurationService;


    @ApiOperation(value = "查询配置信息列表", notes = "传统URL风格,使用RequestBdy参数方式请求")
    @JsonRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Collection<Configuration> listRequestBody(@RequestBody @Valid Configuration configuration, HttpServletRequest request) {
        return list(configuration, request);
    }

    @ApiOperation(value = "查询配置信息列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Collection<Configuration> list(@Valid Configuration configuration, HttpServletRequest request) {
        return configurationService.getForList(configuration);
    }


    @ApiOperation(value = "刷新配置信息", notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "refresh", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Collection<Configuration> refresh(@RequestParam("appId") String appId,
                                             @RequestParam(value = "name", required = false) String name,
                                             HttpServletRequest request) {
        if (StringUtils.hasLength(appId) && StringUtils.hasLength(name)) {
            Configuration nameConfig = configurationService.refresh(appId, name);
            return Arrays.asList(nameConfig);
        } else {
            return configurationService.refresh(appId).values();
        }
    }

}
