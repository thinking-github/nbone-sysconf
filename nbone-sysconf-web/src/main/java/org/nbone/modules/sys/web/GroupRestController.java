package org.nbone.modules.sys.web;

import io.swagger.annotations.*;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.modules.sys.domain.request.GroupQuery;
import org.nbone.modules.sys.entity.Dict;
import org.nbone.modules.sys.entity.Group;
import org.nbone.modules.sys.service.GroupService;
import org.nbone.modules.sys.service.impl.GroupServiceImpl;
import org.nbone.mvc.rest.ApiResponse;
import org.nbone.mvc.web.FormController;
import org.nbone.web.util.RequestQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-17
 */
@Api(tags = {"分组管理"})
@RestController
@RequestMapping(value = {"sys/group"})
public class GroupRestController<T extends Group> extends BaseController<Group> implements FormController<Group, Integer> {

    public final static String ENTITY_NAME = "分组";
    @Resource
    private GroupService groupService;


    @Override
    public void preHandle(Group entityRequest, HttpServletRequest request) {
        if (entityRequest != null) {
            super.setDynamicTableName(entityRequest, request);
            Object type = request.getAttribute(Dict.DICT_TYPE_NAME);
            if (!ObjectUtils.isEmpty(type)) {
                entityRequest.setType(type.toString());
            }
        }
    }

    @Override
    public void updateBefore(Group entityRequest, HttpServletRequest request) {
        entityRequest.setUpdateTime(new Date());
    }

    @Override
    public void saveBefore(Group entityRequest, HttpServletRequest request) {
        entityRequest.setCreateTime(new Date());
        Assert.notNull(entityRequest.getCreateBy(),"创建者不能为空!");

        if (entityRequest.getSort() == null) {
            entityRequest.setSort(0);
        }
        Integer pid = entityRequest.getParentId();
        if ((pid != null && pid != 0) && entityRequest.getLevel() == null) {
            entityRequest.setLevel(2);
        }
    }


    /**
     * @param group
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "添加", notes = "REST风格,使用RequestBody参数方式请求",
            extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonPostMapping(value = {"", "add"})
    @ResultResponseBody
    public boolean addRequestBody(@RequestBody @Valid Group group, HttpServletRequest request) {
        return add(group, request);
    }

    /**
     * @param group
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "修改", notes = "REST风格,使用RequestBody参数方式请求"
            , extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonRequestMapping(method = RequestMethod.PUT)
    @ResultResponseBody
    public boolean updateRequestBody(@RequestBody Group group, HttpServletRequest request) {
        return update(group, request);
    }

    /**
     * 传统 Action api
     *
     * @param group
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "修改", notes = "传统URL风格,使用RequestBody参数方式请求"
            , extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonRequestMapping(value = "update", method = {RequestMethod.POST})
    @ResultResponseBody
    public boolean updateRequestBodyAction(@RequestBody Group group, HttpServletRequest request) {
        return update(group, request);
    }

    @Override
    public boolean add(@Valid Group group, HttpServletRequest request) {
        preHandle(group, request);
        saveOrUpdateBefore(group, request);
        saveBefore(group, request);
        groupService.insert(group);
        return true;
    }

    @Override
    public boolean update(Group group, HttpServletRequest request) {
        Integer dictId = group.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(group, request);
        saveOrUpdateBefore(group, request);
        updateBefore(group, request);
        groupService.updateSelective(group);
        return true;
    }
    @Override
    public boolean updateAction(Group group, HttpServletRequest request) {
        return update(group, request);
    }

    @Override
    public boolean patch(Group group, HttpServletRequest request) {
        Integer dictId = group.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(group, request);
        saveOrUpdateBefore(group, request);
        updateBefore(group, request);
        groupService.updateSelective(group);
        return true;
    }

    @Override
    public boolean delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null, request);
        disableBefore(id,request);
        String tableName = super.getDynamicTableName(request);
        groupService.delete(id, tableName);
        return true;
    }
    @Override
    public boolean deleteAction(@RequestParam("id") Integer id, HttpServletRequest request) {
        return delete(id, request);
    }

    @ApiOperation(value = "查询详情信息impl", notes = "REST风格,使用PathVariable参数方式请求", response = Group.class)
    @Override
    public Object get(@PathVariable("id") Integer id, HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null, request);

        String tableName = super.getDynamicTableName(request);
        Group group = groupService.get(id, tableName);
        return ApiResponse.success(group);
    }

    @ApiOperation(value = "查询详情信息impl", notes = "传统URL风格", response = Group.class)
    @Override
    public Object info(Integer id, HttpServletRequest request) {
        return get(id, request);
    }


    @ApiOperation(value = "查询信息列表", notes = "传统URL风格,使用RequestBdy参数方式请求")
    @JsonRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<Group> listRequestBody(@RequestBody T dict, HttpServletRequest request) {
        return list(dict, request);
    }

    @ApiOperation(value = "查询信息列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<Group> list(T group, HttpServletRequest request) {
        preHandle(group, request);
        queryBefore(group, request);
        Boolean bool = ServletRequestUtils.getBooleanParameter(request,"parent",true);
        return groupService.getForList(group,null, bool);
    }

    @ApiOperation(value = "查询组标识、名称列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "names", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<Group> groupIdNames(GroupQuery groupQuery, HttpServletRequest request) {
        Group group =  groupQuery.convert();
        preHandle(group, request);
        queryBefore(group, request);
        return groupService.getForList(group, GroupServiceImpl.ENUM_FIELDS, false);
    }


    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用RequestBody参数方式请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int",example = "20"),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", paramType = "query", dataType = "string",example = "id desc")})
    @JsonRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Page<Group> getPageRequestBody(@RequestBody T group, HttpServletRequest request) {
        return getPage(group, request);
    }

    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int",example = "20"),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", paramType = "query", dataType = "string",example = "id desc")})
    @FormRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Page<Group> getPage(T group, HttpServletRequest request) {
        preHandle(group, request);
        queryBefore(group, request);
        int pageNum = RequestQueryUtils.getPageNum(request);
        int pageSize = RequestQueryUtils.getPageSize(request);
        //String orderBy = RequestQueryUtils.getOrderBy(request);
        Boolean bool = ServletRequestUtils.getBooleanParameter(request,"parent",true);

        Page page = groupService.getForPage(group, pageNum, pageSize,bool);
        //DataGrid<Dict> dictDataGrid =dictService.
        return page;
    }

}
