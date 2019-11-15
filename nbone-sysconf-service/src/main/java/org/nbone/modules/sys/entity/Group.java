package org.nbone.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import org.nbone.persistence.entity.DataStateEntity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
public class Group extends DataStateEntity<Group, Integer> implements Serializable {

    private static final long serialVersionUID = 825592690549901092L;

    private String name;	   // 名称
    private String value;	   // 数据值
    private String label;	   // 标签名
    private String type;	   // 类型
    private String description;// 描述

    private Integer sort;	   // 排序

    private String parentId;   //父Id
    private Integer level;     //级别

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
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
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
}
