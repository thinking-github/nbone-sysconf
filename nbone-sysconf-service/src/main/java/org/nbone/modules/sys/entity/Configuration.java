package org.nbone.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.nbone.validation.groups.Update;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-29
 */
@Entity(name = "sys_configuration")
@ApiModel
public class Configuration implements Serializable {

    private static final long serialVersionUID = 4565778198157469655L;

    public final static String TABLE_NAME = "sys_configuration";

    /**
     * 实体编号（唯一标识）
     */
    @NotNull(groups = Update.class)
    @javax.persistence.Id
    protected Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "数据值")
    private String value;

    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * app id 支持多产品
     */
    @NotNull
    @ApiModelProperty(value = "appId")
    @Column(name = "app_id")
    private String appId;
    /**
     * 产品包名
     */
    @ApiModelProperty(value = "产品包名")
    @Column(name = "package_name")
    private String packageName;


    @ApiModelProperty(value = "创建时间", readOnly = true)
    @Column(name = "create_time")
    protected Date createTime;

    @ApiModelProperty(value = "更新时间", readOnly = true)
    @Column(name = "update_time")
    protected Date updateTime;

    /**
     * 删除标记（0：正常；1：删除）
     */
    @ApiModelProperty(value = "删除标记", readOnly = true)
    protected Integer deleted;


    public Configuration() {
    }

    public Configuration(String appId) {
        this.appId = appId;
    }

    public Configuration(String appId, String name) {
        this.appId = appId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
