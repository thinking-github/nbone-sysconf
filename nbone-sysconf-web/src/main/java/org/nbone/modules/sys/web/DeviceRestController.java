package org.nbone.modules.sys.web;

import io.swagger.annotations.*;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.modules.sys.entity.Device;
import org.nbone.modules.sys.entity.Dict;
import org.nbone.modules.sys.entity.Group;
import org.nbone.modules.sys.service.DeviceService;
import org.nbone.mvc.rest.ApiResponse;
import org.nbone.mvc.web.FormController;
import org.nbone.web.util.RequestQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
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
@Api(tags = {"设备管理"})
@RestController
@RequestMapping(value = {"${nbone.sysconf.base.path:sys}/device"})
public class DeviceRestController<T extends Device> extends BaseController<Device>
        implements FormController<Device, Long> {

    public final static String ENTITY_NAME = "设备";
    @Resource
    private DeviceService deviceService;


    @Override
    public void preHandle(Device entityRequest, HttpServletRequest request) {
        if (entityRequest != null) {
            super.setDynamicTableName(entityRequest, request);
        }
    }

    @Override
    public void updateBefore(Device entityRequest, HttpServletRequest request) {
        entityRequest.setUpdateTime(new Date());
    }

    @Override
    public void saveBefore(Device entityRequest, HttpServletRequest request) {
        entityRequest.setCreateTime(new Date());
        Assert.notNull(entityRequest.getCreateBy(), "创建者不能为空!");

        if (entityRequest.getSort() == null) {
            entityRequest.setSort(0);
        }
    }


    /**
     * @param device
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "添加", notes = "REST风格,使用RequestBody参数方式请求",
            extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonPostMapping(value = {"", "add"})
    @ResultResponseBody
    public boolean addRequestBody(@RequestBody @Valid Device device, HttpServletRequest request) {
        return add(device, request);
    }

    /**
     * @param device
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "修改", notes = "REST风格,使用RequestBody参数方式请求"
            , extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonRequestMapping(method = RequestMethod.PUT)
    @ResultResponseBody
    public boolean updateRequestBody(@RequestBody Device device, HttpServletRequest request) {
        return update(device, request);
    }

    /**
     * 传统 Action api
     *
     * @param device
     * @return
     */
    @ApiOperation(value = ENTITY_NAME + "修改", notes = "传统URL风格,使用RequestBody参数方式请求"
            , extensions = @Extension(properties = @ExtensionProperty(name = "update", value = "1")))
    @JsonRequestMapping(value = "update", method = {RequestMethod.POST})
    @ResultResponseBody
    public boolean updateRequestBodyAction(@RequestBody Device device, HttpServletRequest request) {
        return update(device, request);
    }

    @Override
    public boolean add(@Valid Device device, HttpServletRequest request) {
        preHandle(device, request);
        saveOrUpdateBefore(device, request);
        saveBefore(device, request);
        deviceService.insert(device);
        return true;
    }

    @Override
    public boolean update(Device device, HttpServletRequest request) {
        Long dictId = device.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(device, request);
        saveOrUpdateBefore(device, request);
        updateBefore(device, request);
        deviceService.updateSelective(device);
        return true;
    }

    @Override
    public boolean updateAction(Device device, HttpServletRequest request) {
        return update(device, request);
    }

    @Override
    public boolean patch(Device device, HttpServletRequest request) {
        Long dictId = device.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(device, request);
        saveOrUpdateBefore(device, request);
        updateBefore(device, request);
        deviceService.updateSelective(device);
        return true;
    }

    @Override
    public boolean delete(@PathVariable("id") Long id, HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null, request);
        disableBefore(id, request);
        String tableName = super.getDynamicTableName(request);
        deviceService.delete(id, tableName);
        return true;
    }

    @Override
    public boolean deleteAction(@RequestParam("id") Long id, HttpServletRequest request) {
        return delete(id, request);
    }

    @ApiOperation(value = "查询详情信息impl", notes = "REST风格,使用PathVariable参数方式请求", response = Group.class)
    @Override
    public Object get(@PathVariable("id") Long id, HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null, request);

        String tableName = super.getDynamicTableName(request);
        Device group = deviceService.get(id, tableName);
        return ApiResponse.success(group);
    }

    @ApiOperation(value = "查询详情信息impl", notes = "传统URL风格", response = Group.class)
    @Override
    public Object info(Long id, HttpServletRequest request) {
        return get(id, request);
    }


    @ApiOperation(value = "查询信息列表", notes = "传统URL风格,使用RequestBdy参数方式请求")
    @JsonRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<Device> listRequestBody(@RequestBody T device, HttpServletRequest request) {
        return list(device, request);
    }

    @ApiOperation(value = "查询信息列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<Device> list(T device, HttpServletRequest request) {
        preHandle(device, request);
        queryBefore(device, request);
        return deviceService.getForList(device);
    }


    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用RequestBody参数方式请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int", example = "20"),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", paramType = "query", dataType = "string", example = "id desc")})
    @JsonRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Page<Device> getPageRequestBody(@RequestBody T group, HttpServletRequest request) {
        return getPage(group, request);
    }

    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用Form/Query参数方式请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", paramType = "query", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "单页数量", paramType = "query", dataType = "int", example = "20"),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", paramType = "query", dataType = "string", example = "id desc")})
    @FormRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public Page<Device> getPage(T group, HttpServletRequest request) {
        preHandle(group, request);
        queryBefore(group, request);
        int pageNum = RequestQueryUtils.getPageNum(request);
        int pageSize = RequestQueryUtils.getPageSize(request);
        //String orderBy = RequestQueryUtils.getOrderBy(request);

        Page page = deviceService.getForPage(group, null, pageNum, pageSize);
        //DataGrid<Dict> dictDataGrid =dictService.
        return page;
    }

}
