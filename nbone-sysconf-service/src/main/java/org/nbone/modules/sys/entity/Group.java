package org.nbone.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import org.nbone.persistence.entity.AbstractStateEntity;
import org.nbone.persistence.entity.DynamicTableName;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
@Entity(name = "sys_group")
public class Group extends AbstractStateEntity<Group, Integer> implements DynamicTableName,Serializable {

    private static final long serialVersionUID = 825592690549901092L;

    public final static String TABLE_NAME = "sys_group";

    private String name;	   // 名称
    private String value;	   // 数据值
    private String label;	   // 标签名
    private String type;	   // 类型
    private String description;// 描述

    private Integer sort;	   // 排序

    private Integer parentId;   //父Id
    private Integer level;     //级别


    /**
     * 支持动态表和分表查询
     */
    @Transient
    private transient String tableName;

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

    @XmlAttribute
    @Length(min=0, max=100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Length(min=1, max=100)
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
