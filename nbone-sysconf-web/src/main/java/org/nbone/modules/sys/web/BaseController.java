package org.nbone.modules.sys.web;

import org.nbone.persistence.entity.DynamicTableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-14
 */
public abstract class BaseController {

    /**
     * 日志对象
     */
    protected static Logger logger = LoggerFactory.getLogger("sysconfController");


    protected void preHandle(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void setDynamicTableName(DynamicTableName dynamicTableName, HttpServletRequest request) {
        String tableName = (String) request.getAttribute(DynamicTableName.TABLE_NAME_KEY);
        if (StringUtils.hasLength(tableName)) {
            dynamicTableName.setTableName(tableName);
        }
    }

    protected String getDynamicTableName(HttpServletRequest request) {
        String tableName = (String) request.getAttribute(DynamicTableName.TABLE_NAME_KEY);
        return tableName;
    }


}
