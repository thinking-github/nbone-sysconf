package org.nbone.modules.sys.web;

import org.nbone.mvc.web.PreparedHandler;
import org.nbone.persistence.entity.DynamicTableName;
import org.nbone.persistence.entity.TypeAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-14
 */
public abstract class BaseController<T> implements PreparedHandler<T> {

    /**
     * 日志对象
     */
    protected static Logger logger = LoggerFactory.getLogger("sysconfController");


    protected void preHandle(HttpServletRequest request, HttpServletResponse response) {

    }


    @Override
    public void saveOrUpdateBefore(T entityRequest, HttpServletRequest request) {

    }

    public void disableBefore(Serializable id, HttpServletRequest request) {

    }

    @Override
    public void queryBefore(T entityRequest, HttpServletRequest request) {

    }


    protected void setDynamicTableName(DynamicTableName dynamicTableName, HttpServletRequest request) {
        String tableName = (String) request.getAttribute(DynamicTableName.TABLE_NAME_KEY);
        if (StringUtils.hasLength(tableName)) {
            dynamicTableName.setTableName(tableName);
        }
    }

    protected void setResourceType(TypeAttribute typeAttribute, HttpServletRequest request) {
        String type = (String) request.getAttribute(TypeAttribute.TYPE_KEY);
        if (StringUtils.hasLength(type)) {
            typeAttribute.setType(type);
        }
    }

    protected String getDynamicTableName(HttpServletRequest request) {
        String tableName = (String) request.getAttribute(DynamicTableName.TABLE_NAME_KEY);
        return tableName;
    }


}
