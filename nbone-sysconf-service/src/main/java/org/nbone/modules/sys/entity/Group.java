package org.nbone.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.nbone.mvc.domain.ParentDomain;
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
 * @author thinking
 * @version 1.0
 * @since 2018-11-15
 */
@Entity(name = "sys_group")
public class Group extends AbstractStateEntity<Group, Integer>
        implements DynamicTableName, ParentDomain<Integer>,Serializable {

    private static final long serialVersionUID = 825592690549901092L;

    public final static String TABLE_NAME = "sys_group";
    public final static String GROUP_TYPE_NAME = "group_type";

    @ApiModelProperty(value = "名称")
    private String name;	   // 名称

    @Transient
    private String value;	   // 数据值
    @Transient
    private String label;	   // 标签名

    @ApiModelProperty(value = "描述")
    private String description;// 描述
    @ApiModelProperty(value = "类型")
    private String type;	   // 类型

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

    @ApiModelProperty(value = "上级名称",readOnly = true)
    @Transient
    private String parentName;


    @ApiModelProperty(value = "级别",readOnly = true)
    private Integer level;     //级别


    /**
     * 支持动态表和分表查询
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @Transient
    private transient String tableName;

    @ApiModelProperty(value = "查询排序",readOnly = true)
    @Transient
    private String orderBy;

    public Group() {
    }

    public Group(Integer id){
        super(id,null);
    }

    public Group(String value, String label){
        this.value = value;
        this.label = label;
    }
    @XmlAttribute
    @Length(min=1, max=100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group{");
        sb.append("name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
