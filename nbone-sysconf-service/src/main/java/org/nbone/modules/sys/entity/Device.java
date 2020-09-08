package org.nbone.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.nbone.persistence.entity.DynamicTableName;
import org.nbone.validation.groups.Update;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static org.nbone.modules.sys.entity.Device.TABLE_NAME;

/**
 * 设备配置实体
 *
 * @author thinking
 * @version 1.0
 * @since 2019-05-27
 */
@Entity(name = TABLE_NAME)
@ApiModel
public class Device implements DynamicTableName,Serializable {

    private static final long serialVersionUID = 3589001421882241445L;

    public final static String TABLE_NAME = "sys_device";

    /**
     * 实体编号（唯一标识）
     */
    @NotNull(groups = Update.class)
    @javax.persistence.Id
    protected Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "设备id")
    @Column(name = "device_id")
    private String deviceId;

    @ApiModelProperty(value = "设备型号")
    @Column(name = "device_model")
    private String deviceModel;

    @ApiModelProperty(value = "客户端id")
    @Column(name = "client_id")
    private String clientId;


    @ApiModelProperty(value = "类型")
    private String type;


    @ApiModelProperty(value = "使用者名称")
    private String uname;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;


    @ApiModelProperty(value = "创建时间", readOnly = true)
    @Column(name = "create_time")
    protected Date createTime;

    @ApiModelProperty(value = "创建者")
    @Column(name = "create_by")
    protected String createBy;

    @ApiModelProperty(value = "更新时间", readOnly = true)
    @Column(name = "update_time")
    protected Date updateTime;

    @ApiModelProperty(value = "更新者")
    @Column(name = "update_by")
    protected String updateBy;

    @ApiModelProperty(value = "备注信息", readOnly = true)
    protected String remarks;
    /**
     * 删除标记（0：正常；1：删除）
     */
    @ApiModelProperty(value = "删除标记", readOnly = true)
    protected Integer deleted;


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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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
