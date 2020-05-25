package org.nbone.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.nbone.persistence.entity.AbstractStateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * 应用实体
 *
 * @author thinking
 * @version 1.0
 * @since 2018-11-15
 */
@Entity(name = "application")
@ApiModel
public class Application extends AbstractStateEntity<Application, Integer>
        implements Serializable {
    /**
     * app id
     */
    @ApiModelProperty(value = "appId")
    @Column(name = "app_id")
    private String appId;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    private String name;

    /**
     * 英文名称
     */
    @ApiModelProperty(value = "英文名称")
    @Column(name = "en_name")
    private String enName;

    /**
     * 显示名称
     */
    @ApiModelProperty(value = "显示名称")
    @Column(name = "display_name")
    private String displayName;


    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;
    /**
     * 产品域名
     */
    @ApiModelProperty(value = "产品域名")
    private String domain;

    /**
     * 图片域名
     */
    @ApiModelProperty(value = "图片域名")
    @Column(name = "image_domain")
    private String imageDomain;
    /**
     * 文件域名
     */
    @ApiModelProperty(value = "文件域名")
    @Column(name = "file_domain")
    private String fileDomain;

    /**
     * 产品包名
     */
    @ApiModelProperty(value = "产品包名")
    @Column(name = "package_name")
    private String packageName;

    public Application(String appId, String name, String packageName, String domain) {
        this.appId = appId;
        this.name = name;
        this.packageName = packageName;
        this.domain = domain;
    }

    public Application(String appId, String name, String packageName) {
        this(appId, name, packageName, null);
    }

    public Application() {
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getDisplayName() {
        if (displayName != null) {
            return displayName;
        }
        return name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getImageDomain() {
        return imageDomain;
    }

    public void setImageDomain(String imageDomain) {
        this.imageDomain = imageDomain;
    }

    public String getFileDomain() {
        return fileDomain;
    }

    public void setFileDomain(String fileDomain) {
        this.fileDomain = fileDomain;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
