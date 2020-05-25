package org.nbone.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author thinking
 * @version 1.0
 * @since 2018-11-15
 */
@Entity(name = "sys_product")
@ApiModel
public class Product implements Serializable {
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

    public Product(String appId, String name, String packageName, String domain) {
        this.appId = appId;
        this.name = name;
        this.packageName = packageName;
        this.domain = domain;
    }

    public Product(String appId, String name, String packageName) {
        this(appId, name, packageName, null);
    }

    public Product() {
    }
    /**
     * productId
     */
    //@ApiModelProperty(value = "产品id")
    //private Long productId;


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
