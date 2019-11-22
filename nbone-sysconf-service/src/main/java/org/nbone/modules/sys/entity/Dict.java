package org.nbone.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.nbone.persistence.entity.AbstractStateEntity;
import org.nbone.persistence.entity.DynamicTableName;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 *
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Entity(name = "sys_dict")
@ApiModel
public class Dict extends AbstractStateEntity<Dict,Integer> implements DynamicTableName, Serializable {

    private static final long serialVersionUID = 3574080129436762841L;

    public final static String TABLE_NAME = "sys_dict";
    public final static String DICT_TYPE_NAME = "dict_type";


    private String value;	// 数据值
    private String label;	// 标签名

    @ApiModelProperty(value = "描述")
    private String description;// 描述

    @ApiModelProperty(value = "类型")
    private String type;	// 类型


    @ApiModelProperty(hidden = true)
    @Column(name = "app_id")
    private String appId;	// app id 支持多产品
    /**
     *  产品包名
     */
    @ApiModelProperty(value = "产品包名")
    @Column(name = "package_name")
    private String packageName;


    @ApiModelProperty(value = "排序字段")
    private Integer sort;	   // 排序

    @ApiModelProperty(value = "上级id")
    @Column(name = "parent_id")
    private Integer parentId;   //父Id

    @ApiModelProperty(readOnly = true)
    private Integer level;     //级别

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @Transient
    private transient String tableName;

    public Dict() {
    }

    public Dict(Integer id){
        super(id,null);
    }

    public Dict(String value, String label){
        this.value = value;
        this.label = label;
    }

    @XmlAttribute
    @Length(min=1, max=100)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    @Length(min=1, max=100)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Length(min=1, max=100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ApiModelProperty(value =  "状态 0:禁用 1:启用" ,allowableValues = "0,1")
    @Override
    public Integer getStatus() {
        return super.getStatus();
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

    @XmlAttribute
    @Length(min=0, max=100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return label;
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
