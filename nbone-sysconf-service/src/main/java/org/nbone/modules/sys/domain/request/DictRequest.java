package org.nbone.modules.sys.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-21
 */
@Data
public class DictRequest {

    @NotNull
    private Integer id;

    @ApiModelProperty(value = "appId")
    private String appId;    // app id 支持多产品
    /**
     * 产品包名
     */
    @NotNull
    @ApiModelProperty(value = "产品包名")
    private String packageName;

    private String value;    // 数据值
    private String label;    // 标签名
    private String description;// 描述
    private String type;    // 类型

    @ApiModelProperty(value = "状态")
    protected Integer status;

}
