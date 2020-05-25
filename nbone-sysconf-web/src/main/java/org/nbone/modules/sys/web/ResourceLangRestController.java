package org.nbone.modules.sys.web;

import io.swagger.annotations.*;
import org.nbone.framework.spring.support.ComponentFactory;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.modules.sys.entity.ResourceLanguage;
import org.nbone.modules.sys.service.ResourceLanguageService;
import org.nbone.mvc.rest.ApiResponse;
import org.nbone.mvc.web.FormController;
import org.nbone.util.DateFPUtils;
import org.nbone.web.util.RequestQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Api(tags = {"资源多语言设置"})
@RestController
@RequestMapping(value = {"sys/resource/language"})
public class ResourceLangRestController<T extends ResourceLanguage> extends BaseController<T>
        implements FormController<T, Integer> {

    public final static String ENTITY_NAME = "资源多语言";

    @Resource
    private ResourceLanguageService resourceLanguageService;

    @Resource
    private Validator validator;

    @Resource
    private SmartValidator smartValidator;

    static Method batchAddMethod;
    static {
        Class[] paramTypes = new Class[]{List.class,HttpServletRequest.class};
         batchAddMethod = ReflectionUtils.findMethod(ResourceLangRestController.class,"batchAddRequestBody",paramTypes);
    }

    //自定义数据模型格式化
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateFPUtils.parseDateMultiplePattern(text));
            }
        });
    }

    @Override
    public void preHandle(ResourceLanguage entityRequest, HttpServletRequest request) {
        if (entityRequest != null) {
            super.setDynamicTableName(entityRequest, request);
            super.setResourceType(entityRequest,request);
        }
    }


    @Override
    public void updateBefore(ResourceLanguage entityRequest, HttpServletRequest request) {
        entityRequest.setUpdateTime(new Date());
    }

    @Override
    public void saveBefore(ResourceLanguage entityRequest, HttpServletRequest request) {
        Date now = new Date();
        entityRequest.setCreateTime(now);
        entityRequest.setUpdateTime(now);
        entityRequest.setDeleted(0);
        if (entityRequest.getSort() == null) {
            entityRequest.setSort(0);
        }
    }

    @Override
    public void saveOrUpdateBefore(ResourceLanguage entityRequest, HttpServletRequest request) {
    }

    /**
     * @param resourceLanguage
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "添加", notes = "REST风格,使用RequestBody参数方式请求",
            extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonPostMapping(value = {"", "add"})
    @ResultResponseBody
    public boolean addRequestBody(@RequestBody @Valid T resourceLanguage, HttpServletRequest request) {
        return add(resourceLanguage, request);
    }

    /**
     * @param resourceLanguages
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "批量添加", notes = "REST风格,使用RequestBody参数方式请求",
            extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonPostMapping(value = {"/batch/add"})
    @ResultResponseBody
    public boolean batchAddRequestBody(@RequestBody @Valid List<T> resourceLanguages,
                                       HttpServletRequest request) {
        Assert.notEmpty(resourceLanguages, "resource language parameter not empty.");
        ComponentFactory.validate(resourceLanguages);

    /*    Set<ConstraintViolation<ResourceLangRestController<T>>> result;
        Validator nativeValidator  = validator.unwrap(Validator.class);
        result = nativeValidator.forExecutables().validateParameters(this,batchAddMethod,new Object[]{resourceLanguages,request});
        if (!result.isEmpty()) {
            throw new ConstraintViolationException(result);
        }*/

        for (ResourceLanguage resourceLanguage : resourceLanguages) {
            preHandle(resourceLanguage, request);
            saveOrUpdateBefore(resourceLanguage, request);
            saveBefore(resourceLanguage, request);
        }
        resourceLanguageService.batchInsert(resourceLanguages);
        return true;
    }

    /**
     * @param resourceLanguage
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "修改", notes = "REST风格,使用RequestBody参数方式请求"
            , extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonRequestMapping(method = RequestMethod.PUT)
    @ResultResponseBody
    public boolean updateRequestBody(@RequestBody T resourceLanguage, HttpServletRequest request) {
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
    public boolean updateRequestBodyAction(@RequestBody T resourceLanguage, HttpServletRequest request) {
        return update(resourceLanguage, request);
    }

    @Override
    public boolean add(@Valid T resourceLanguage, HttpServletRequest request) {
        preHandle(resourceLanguage, request);
        saveOrUpdateBefore(resourceLanguage, request);
        saveBefore(resourceLanguage, request);

        resourceLanguageService.insert(resourceLanguage);
        return true;
    }

    @Override
    public boolean update(T resourceLanguage, HttpServletRequest request) {
        Integer dictId = resourceLanguage.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(resourceLanguage, request);
        saveOrUpdateBefore(resourceLanguage, request);
        updateBefore(resourceLanguage, request);
        resourceLanguageService.updateSelective(resourceLanguage);
        return true;
    }

    @Override
    public boolean updateAction(T resourceLanguage, HttpServletRequest request) {
        return update(resourceLanguage, request);
    }

    @Override
    public boolean patch(T resourceLanguage, HttpServletRequest request) {
        Integer dictId = resourceLanguage.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(resourceLanguage, request);
        saveOrUpdateBefore(resourceLanguage, request);
        updateBefore(resourceLanguage, request);
        resourceLanguageService.updateSelective(resourceLanguage);
        return true;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int",example = "20")})
    @JsonRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Page getPageRequestBody(@RequestBody T resourceLanguage, HttpServletRequest request) {
        return getPage(resourceLanguage, request);
    }

    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int",example = "20")})
    @FormRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
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
