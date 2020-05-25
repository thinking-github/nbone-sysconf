package org.nbone.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.nbone.persistence.entity.AbstractStateEntity;
import org.nbone.persistence.entity.DynamicTableName;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * 字典key 对应value 的实体数据
 *
 * @author thinking
 * @version 1.0
 * @since 2018-11-15
 */
@Entity(name = "sys_dict_value")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel
public class DictValue extends AbstractStateEntity<DictValue, Integer>
        implements DynamicTableName, Serializable {

    private static final long serialVersionUID = 812530956843143543L;

    public final static String TABLE_NAME = "sys_dict_value";

    @ApiModelProperty(value = "字典表id")
    @Column(name = "dict_id")
    private Integer dictId;

    @ApiModelProperty(value = "数据值")
    private String value;

    @ApiModelProperty(value = "显示名称")
    private String label;    // 标签名

    @ApiModelProperty(value = "描述")
    private String description;// 描述

    @ApiModelProperty(value = "类型")
    private String type;    // 类型

    @ApiModelProperty(value = "排序字段")
    private Integer sort;       // 排序

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @Transient
    private transient String tableName;

    public DictValue() {
    }

    public DictValue(Integer id) {
        super(id, null);
    }

    public DictValue(String value, String label) {
        this(null, value, label);
    }

    public DictValue(Integer dictId, String value, String label) {
        this.dictId = dictId;
        this.value = value;
        this.label = label;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    @XmlAttribute
    @Length(min = 1, max = 100)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    @Length(min = 1, max = 100)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Length(min = 1, max = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ApiModelProperty(value = "状态 0:禁用 1:启用", allowableValues = "0,1")
    @Override
    public Integer getStatus() {
        return super.getStatus();
    }

    @XmlAttribute
    @Length(min = 0, max = 100)
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DictValue{");
        sb.append("value='").append(value).append('\'');
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
        if (StringUtils.hasLength(tableName)) {
            return tableName;
        }
        return TABLE_NAME;
    }
}
