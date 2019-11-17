package org.nbone.modules.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.nbone.modules.sys.entity.Dict;
import org.nbone.modules.sys.service.DictService;
import org.nbone.web.util.RequestQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Api(tags = { "字典管理" })
@RestController
@RequestMapping(value = {"sys/dict"})
public class DictRestController extends BaseController {

    @Resource
    private DictService dictService;

    /**
     * @param dict
     * @return
     */
    @ApiOperation(value = "字典添加",notes = "REST风格,使用RequestBody参数方式请求")
    @RequestMapping(method = RequestMethod.POST)
    public boolean addRequestBody(@RequestBody @Valid Dict dict,HttpServletRequest request) {
        return add(dict,request);
    }

    @ApiOperation(value = "字典添加",notes = "REST风格,使用Form参数方式请求")
    @FormPostMapping()
    public boolean add(@Valid Dict dict,HttpServletRequest request) {
        super.setDynamicTableName(dict,request);
        dictService.insert(dict);
        return true;
    }

    /**
     * 传统 Action api
     *
     * @param dict
     * @return
     */
    @ApiOperation(value = "字典添加",notes = "传统URL风格,使用RequestBody参数方式请求")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean addRequestBodyAction(@RequestBody @Valid Dict dict,HttpServletRequest request) {
        return add(dict,request);
    }
    @ApiOperation(value = "字典添加",notes = "传统URL风格,使用Form表单参数方式请求")
    @FormPostMapping(value = "add")
    public boolean addAction(@Valid Dict dict,HttpServletRequest request) {
        return add(dict,request);
    }


    /**
     * @param dict
     * @return
     */
    @ApiOperation(value = "字典修改",notes = "REST风格,使用RequestBody参数方式请求")
    @RequestMapping(method = RequestMethod.PUT)
    public boolean updateRequestBody(@RequestBody Dict dict,HttpServletRequest request) {
        return update(dict,request);
    }
    @ApiOperation(value = "字典修改",notes = "REST风格,使用Form参数方式请求")
    @FormRequestMapping(method = RequestMethod.PUT)
    public boolean update(Dict dict,HttpServletRequest request) {
        Integer dictId = dict.getId();
        Assert.notNull(dictId, "id输入参数不能为空.");
        super.setDynamicTableName(dict,request);
        dictService.updateSelective(dict);
        return true;
    }

    /**
     * 传统 Action api
     * @param dict
     * @return
     */
    @ApiOperation(value = "字典修改",notes = "传统URL风格,使用RequestBody参数方式请求")
    @RequestMapping(value = "update", method = {RequestMethod.POST, RequestMethod.PUT})
    public boolean updateRequestBodyAction(@RequestBody Dict dict,HttpServletRequest request) {
        return update(dict,request);
    }
    @ApiOperation(value = "字典修改",notes = "传统URL风格,使用Form表单参数方式请求")
    @FormRequestMapping(value = "update", method = {RequestMethod.POST, RequestMethod.PUT})
    public boolean updateAction(Dict dict,HttpServletRequest request) {
        return update(dict,request);
    }


    @ApiOperation(value = "字典删除",notes = "REST风格,使用PathVariable参数方式请求")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") Integer id,HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        String tableName  = super.getDynamicTableName(request);
        dictService.delete(id,tableName);
        return true;
    }
    @ApiOperation(value = "字典删除",notes = "传统URL风格")
    @RequestMapping(value = "delete", method = {RequestMethod.POST,RequestMethod.DELETE})
    public boolean deleteAction(@RequestParam("id") Integer id,HttpServletRequest request) {
        return delete(id,request);
    }


    @ApiOperation(value = "查询详情信息",notes = "REST风格,使用PathVariable参数方式请求")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Dict get(@PathVariable("id") Integer id,HttpServletRequest request) {
        Assert.notNull(id, "id输入参数不能为空.");
        String tableName  = super.getDynamicTableName(request);
        return dictService.get(id,tableName);
    }
    @ApiOperation(value = "查询详情信息",notes = "传统URL风格")
    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Dict getRequestParam(@RequestParam("id") Integer id,HttpServletRequest request) {
        return get(id,request);
    }
    @ApiOperation(value = "查询详情信息",notes = "传统URL风格")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Dict info(@RequestParam("id") Integer id,HttpServletRequest request) {
        return get(id,request);
    }

    @ApiOperation(value = "查询信息列表",notes = "传统URL风格,使用RequestBody参数方式请求")
    @JsonRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Dict> listRequestBody(@RequestBody  Dict dict, HttpServletRequest request) {
        return list(dict,request);
    }
    @ApiOperation(value = "查询信息列表",notes = "传统URL风格,使用Form/Query参数方式请求")
    @FormRequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Dict> list(Dict dict, HttpServletRequest request) {
        super.setDynamicTableName(dict,request);
        return dictService.getForList(dict);
    }


    @ApiOperation(value = "查询分页列表",notes = "传统URL风格")
    @ApiImplicitParams({@ApiImplicitParam(name ="pageNum",dataType = "int"),
                        @ApiImplicitParam(name ="pageSize",dataType = "int")})
    @JsonRequestMapping(value = "page", params = "pageNum", method = {RequestMethod.GET, RequestMethod.POST})
    public Page getPageRequestBody(@RequestBody  Dict dict, HttpServletRequest request) {
        return getPage(dict,request);
    }

    @ApiOperation(value = "查询分页列表",notes = "传统URL风格")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", dataType = "int"),
                        @ApiImplicitParam(name = "pageSize", dataType = "int")})
    @FormRequestMapping(value = "page", params = "pageNum", method = {RequestMethod.GET, RequestMethod.POST})
    public Page getPage(Dict dict, HttpServletRequest request) {
        int pageNum = RequestQueryUtils.getPageNum(request);
        int pageSize = RequestQueryUtils.getPageSize(request);
        super.setDynamicTableName(dict,request);
        Page page = dictService.getForPage(dict,null,pageNum,pageSize);
        //DataGrid<Dict> dictDataGrid =dictService.
        return page;
    }


}
