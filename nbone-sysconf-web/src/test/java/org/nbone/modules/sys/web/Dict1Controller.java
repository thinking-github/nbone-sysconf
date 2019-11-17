package org.nbone.modules.sys.web;

import org.nbone.persistence.entity.DynamicTableName;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2019-11-16
 */
@RestController
@RequestMapping("dict1")
public class Dict1Controller extends DictRestController {

    @ModelAttribute
    @Override
    protected void preHandle(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(DynamicTableName.TABLE_NAME_KEY, "dict1");
        System.out.println("dictC1");
    }
}
