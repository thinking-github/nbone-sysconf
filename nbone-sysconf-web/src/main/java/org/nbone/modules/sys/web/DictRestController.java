package org.nbone.modules.sys.web;

import org.nbone.modules.sys.entity.Dict;
import org.nbone.modules.sys.service.DictService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@RestController
@RequestMapping(value = {"dict"})
public class DictRestController extends BaseController {

    @Resource
    private DictService dictService;


    @RequestMapping(value = {"list",""},method = {RequestMethod.GET, RequestMethod.POST})
    public List<Dict> list(Dict dict, HttpServletRequest request, HttpServletResponse response) {

        return dictService.getAll();
    }


}
