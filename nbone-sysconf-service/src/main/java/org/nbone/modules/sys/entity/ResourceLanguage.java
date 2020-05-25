package org.nbone.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.nbone.persistence.entity.BaseEntity;
import org.nbone.persistence.entity.DynamicTableName;
import org.nbone.persistence.entity.TypeAttribute;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author chenyicheng
 * @version 1.0
 * @since 2018-11-24
 */
@Entity(name = "sys_resource_language")
public class ResourceLanguage extends BaseEntity<ResourceLanguage, Integer>
        implements DynamicTableName, TypeAttribute<String>, Serializable {
    private static final long serialVersionUID = 7134114012204829167L;

    public final static String TABLE_NAME = "sys_resource_language";

    @NotNull
    @ApiModelProperty(value = "名称(对应语言)")
    private String name;

    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 语言编码
     */
    @NotNull
    @Column(name = "language_code")
    @ApiModelProperty(value = "语言编码")
    private String languageCode;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "类型", hidden = true, readOnly = true)
    private String type;
    /**
     * 资源id
     */
    @NotNull(message = "resourceId must not be null.")
    @Column(name = "resource_id")
    @ApiModelProperty(value = "资源id")
    private Integer resourceId;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 支持动态表和分表查询
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @Transient
    private transient String tableName;


    public ResourceLanguage() {
    }

    public ResourceLanguage(Integer resourceId) {
        this(resourceId, null);
    }

    public ResourceLanguage(Integer resourceId, String languageCode) {
        this.resourceId = resourceId;
        this.languageCode = languageCode;
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

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    @Override
    public String getTableName() {
        if(StringUtils.hasLength(tableName)){
            return tableName;
        }
        return TABLE_NAME;
    }
}
