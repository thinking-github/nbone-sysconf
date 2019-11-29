package org.nbone.modules.sys.web;

import io.swagger.annotations.*;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.modules.sys.entity.ResourceLanguage;
import org.nbone.modules.sys.service.ResourceLanguageService;
import org.nbone.mvc.rest.ApiResponse;
import org.nbone.mvc.web.FormController;
import org.nbone.web.util.RequestQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Api(tags = {"资源多语言设置"})
@RestController
@RequestMapping(value = {"sys/resource/language"})
public class ResourceLangRestController<T extends ResourceLanguage> extends BaseController<ResourceLanguage>
        implements FormController<ResourceLanguage, Integer> {

    public final static String ENTITY_NAME = "资源多语言";

    @Resource
    private ResourceLanguageService resourceLanguageService;


    @Override
    public void preHandle(ResourceLanguage entityRequest, HttpServletRequest request) {
        if (entityRequest != null) {
            super.setDynamicTableName(entityRequest, request);
        }
    }


    @Override
    public void updateBefore(ResourceLanguage entityRequest, HttpServletRequest request) {
        entityRequest.setUpdateTime(new Date());
    }

    @Override
    public void saveBefore(ResourceLanguage entityRequest, HttpServletRequest request) {
        entityRequest.setCreateTime(new Date());
    }

    /**
     * @param resourceLanguage
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "添加", notes = "REST风格,使用RequestBody参数方式请求",
            extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonPostMapping(value = {"", "add"})
    @ResultResponseBody
    public boolean addRequestBody(@RequestBody @Valid ResourceLanguage resourceLanguage, HttpServletRequest request) {
        return add(resourceLanguage, request);
    }

    /**
     * @param resourceLanguage
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "修改", notes = "REST风格,使用RequestBody参数方式请求"
            , extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonRequestMapping(method = RequestMethod.PUT)
    @ResultResponseBody
    public boolean updateRequestBody(@RequestBody ResourceLanguage resourceLanguage, HttpServletRequest request) {
        return update(resourceLanguage, request);
    }

    /**
     * 传统 Action api
     *
     * @param resourceLanguage
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "修改", notes = "传统URL风格,使用RequestBody参数方式请求"
            , extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonRequestMapping(value = "update", method = {RequestMethod.POST})
    @ResultResponseBody
    public boolean updateRequestBodyAction(@RequestBody ResourceLanguage resourceLanguage, HttpServletRequest request) {
        return update(resourceLanguage, request);
    }

    @Override
    public boolean add(ResourceLanguage resourceLanguage, HttpServletRequest request) {
        preHandle(resourceLanguage, request);
        saveOrUpdateBefore(resourceLanguage, request);
        saveBefore(resourceLanguage, request);

        resourceLanguageService.insert(resourceLanguage);
        return true;
    }

    @Override
    public boolean update(ResourceLanguage resourceLanguage, HttpServletRequest request) {
        Integer dictId = resourceLanguage.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(resourceLanguage, request);
        saveOrUpdateBefore(resourceLanguage, request);
        updateBefore(resourceLanguage, request);
        resourceLanguageService.updateSelective(resourceLanguage);
        return true;
    }

    @Override
    public boolean updateAction(ResourceLanguage resourceLanguage, HttpServletRequest request) {
        return update(resourceLanguage, request);
    }

    @Override
    public boolean delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null, request);
        String tableName = super.getDynamicTableName(request);
        resourceLanguageService.delete(id, tableName);
        return true;
    }

    @Override
    public boolean deleteAction(@RequestParam("id") Integer id, HttpServletRequest request) {
        return delete(id, request);
    }

    @ApiOperation(value = "查询详情信息impl", notes = "REST风格,使用PathVariable参数方式请求", response = ResourceLanguage.class)
    @Override
    public Object get(@PathVariable("id") Integer id, HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null, request);
        String tableName = super.getDynamicTableName(request);
        ResourceLanguage resourceLanguage = resourceLanguageService.get(id, tableName);
        return ApiResponse.success(resourceLanguage);
    }

    @ApiOperation(value = "查询详情信息impl", notes = "传统URL风格", response = ResourceLanguage.class)
    @Override
    public Object info(Integer id, HttpServletRequest request) {
        return get(id, request);
    }


    @ApiOperation(value = "查询信息列表", notes = "传统URL风格,使用RequestBdy参数方式请求")
    @JsonRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<ResourceLanguage> listRequestBody(@RequestBody T resourceLanguage, HttpServletRequest request) {
        return list(resourceLanguage, request);
    }

    @ApiOperation(value = "查询信息列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<ResourceLanguage> list(T resourceLanguage, HttpServletRequest request) {
        preHandle(resourceLanguage, request);
        queryBefore(resourceLanguage, request);
        return resourceLanguageService.getForList(resourceLanguage);
    }


    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用RequestBody参数方式请求")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int")})
    @JsonRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public Page getPageRequestBody(@RequestBody T resourceLanguage, HttpServletRequest request) {
        return getPage(resourceLanguage, request);
    }

    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int")})
    @FormRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public Page getPage(T resourceLanguage, HttpServletRequest request) {
        preHandle(resourceLanguage, request);
        queryBefore(resourceLanguage, request);
        int pageNum = RequestQueryUtils.getPageNum(request);
        int pageSize = RequestQueryUtils.getPageSize(request);
        Page page = resourceLanguageService.getForPage(resourceLanguage, null, pageNum, pageSize);
        //DataGrid<Dict> dictDataGrid =dictService.
        return page;
    }


}
