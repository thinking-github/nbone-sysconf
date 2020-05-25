package org.nbone.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.nbone.persistence.entity.BaseEntity;
import org.nbone.persistence.entity.DynamicTableName;
import org.nbone.persistence.entity.TypeAttribute;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author thinking
 * @version 1.0
 * @since 2020-04-28
 */
@Entity(name = "sys_template")
@ApiModel
public class Template extends BaseEntity<Template, Integer> implements DynamicTableName, TypeAttribute<Integer>, Serializable {

    private static final long serialVersionUID = 520866016843399060L;

    public final static String TABLE_NAME = "sys_template";

    @ApiModelProperty(value = "类型")
    private Integer type;


    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "内容")
    private String content;


    @ApiModelProperty(value = "描述")
    private String description;// 描述


    @ApiModelProperty(hidden = true)
    @Column(name = "app_id")
    private String appId;

    /**
     * 产品包名
     */
    @ApiModelProperty(value = "产品包名")
    @Column(name = "package_name")
    private String packageName;


    /**
     * 支持动态表和分表查询
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @Transient
    private transient String tableName;


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String getTableName() {
        if (StringUtils.hasLength(tableName)) {
            return tableName;
        }
        return TABLE_NAME;
    }
}
