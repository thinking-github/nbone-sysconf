package org.nbone.modules.sys.web;

import io.swagger.annotations.*;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.modules.sys.entity.Dict;
import org.nbone.modules.sys.service.DictService;
import org.nbone.mvc.rest.ApiResponse;
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
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Api(tags = { "字典管理" })
@RestController
@RequestMapping(value = {"sys/dict"})
//@ApiResponses(@ApiResponse(code =400,message ="Bad Request" ,response = org.nbone.mvc.rest.ApiResponse.class))
public class DictRestController<T extends Dict> extends BaseController<Dict> implements FormController<Dict,Integer> {

    public final static String ENTITY_NAME = "字典";
    @Resource
    private DictService dictService;


    @Override
    public void preHandle(Dict entityRequest, HttpServletRequest request) {
        if (entityRequest != null) {
            super.setDynamicTableName(entityRequest, request);
            Object type = request.getAttribute(Dict.DICT_TYPE_NAME);
            if(!ObjectUtils.isEmpty(type)){
                entityRequest.setType(type.toString());
            }
        }
    }

    @Override
    public void saveOrUpdateBefore(Dict entityRequest, HttpServletRequest request) {
    }

    @Override
    public void updateBefore(Dict entityRequest, HttpServletRequest request) {
        entityRequest.setUpdateTime(new Date());
    }

    @Override
    public void saveBefore(Dict entityRequest, HttpServletRequest request) {
        entityRequest.setCreateTime(new Date());
    }

    @Override
    public void queryBefore(Dict entityRequest, HttpServletRequest request) {

    }

    /**
     * @param dict
     * @return
     */
    @ApiOperation(value = ENTITY_NAME+"添加",notes = "REST风格,使用RequestBody参数方式请求",
            extensions=@Extension(properties=@ExtensionProperty(name = "update", value ="1")))
    @JsonPostMapping(value = {"","add"})
    @ResultResponseBody
    public boolean addRequestBody(@RequestBody @Valid Dict dict,HttpServletRequest request) {
        return add(dict,request);
    }

    /**
     * @param dict
     * @return
     */
    @ApiOperation(value = ENTITY_NAME+"修改",notes = "REST风格,使用RequestBody参数方式请求"
            ,extensions=@Extension(properties=@ExtensionProperty(name = "update", value ="1")))
    @JsonRequestMapping(method = RequestMethod.PUT)
    @ResultResponseBody
    public boolean updateRequestBody(@RequestBody Dict dict,HttpServletRequest request) {
        return update(dict,request);
    }

    /**
     * 传统 Action api
     * @param dict
     * @return
     */
    @ApiOperation(value = ENTITY_NAME+"修改",notes = "传统URL风格,使用RequestBody参数方式请求"
            ,extensions=@Extension(properties=@ExtensionProperty(name = "update", value ="1")))
    @JsonRequestMapping(value = "update", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResultResponseBody
    public boolean updateRequestBodyAction(@RequestBody Dict dict,HttpServletRequest request) {
        return update(dict,request);
    }

    @Override
    public boolean add(Dict dict,HttpServletRequest request) {
        preHandle(dict,request);
        saveOrUpdateBefore(dict,request);
        saveBefore(dict,request);

        dictService.insert(dict);
        return true;
    }

    public boolean update(Dict dict,HttpServletRequest request) {
        Integer dictId = dict.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        preHandle(dict,request);
        saveOrUpdateBefore(dict,request);
        updateBefore(dict,request);
        dictService.updateSelective(dict);
        return true;
    }
    public boolean updateAction(Dict dict, HttpServletRequest request) {
        return update(dict,request);
    }


    public boolean delete(@PathVariable("id") Integer id,HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null,request);
        String tableName  = super.getDynamicTableName(request);
        dictService.delete(id,tableName);
        return true;
    }

    public boolean deleteAction(@RequestParam("id") Integer id,HttpServletRequest request) {
        return delete(id,request);
    }

    @ApiOperation(value = "查询详情信息impl",notes = "REST风格,使用PathVariable参数方式请求",response = Dict.class)
    public Object get(@PathVariable("id") Integer id, HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        preHandle(null,request);
        String tableName  = super.getDynamicTableName(request);
        Dict dict = dictService.get(id,tableName);
        return ApiResponse.success(dict);
    }

    @ApiOperation(value = "查询详情信息impl",notes = "传统URL风格",response = Dict.class)
    public Object info(Integer id,HttpServletRequest request) {
        return get(id,request);
    }


    @ApiOperation(value = "查询信息列表",notes = "传统URL风格,使用RequestBdy参数方式请求")
    @JsonRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<Dict> listRequestBody(@RequestBody  T dict, HttpServletRequest request) {
        return list(dict,request);
    }
    @ApiOperation(value = "查询信息列表",notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResultResponseBody
    public List<Dict> list(T dict, HttpServletRequest request) {
        preHandle(dict,request);
        queryBefore(dict,request);
        return dictService.getForList(dict);
    }


    @ApiOperation(value = "查询分页列表", notes = "传统URL风格,使用RequestBody参数方式请求")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value ="查询页码", paramType = "query", dataType = "int"),
                        @ApiImplicitParam(name = "pageSize",value ="单页数量", paramType = "query", dataType = "int")})
    @JsonRequestMapping(value = "page",method = {RequestMethod.GET, RequestMethod.POST})
    public Page getPageRequestBody(@RequestBody  T dict, HttpServletRequest request) {
        return getPage(dict,request);
    }

    @ApiOperation(value = "查询分页列表",notes = "传统URL风格,使用Form/Query参数方式请求")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value ="查询页码", paramType = "query", dataType = "int"),
                        @ApiImplicitParam(name = "pageSize",value ="单页数量", paramType = "query", dataType = "int")})
    @FormRequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public Page getPage(T dict, HttpServletRequest request) {
        preHandle(dict,request);
        queryBefore(dict,request);
        int pageNum = RequestQueryUtils.getPageNum(request);
        int pageSize = RequestQueryUtils.getPageSize(request);
        Page page = dictService.getForPage(dict,null,pageNum,pageSize);
        //DataGrid<Dict> dictDataGrid =dictService.
        return page;
    }



}
