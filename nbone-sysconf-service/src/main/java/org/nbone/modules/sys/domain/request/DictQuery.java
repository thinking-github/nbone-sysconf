package org.nbone.modules.sys.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nbone.modules.sys.entity.Dict;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-12-26
 */
@Data
public class DictQuery {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "数据值")
    private String value;	// 数据值
    @ApiModelProperty(value = "显示名称")
    private String label;	// 标签名

    @ApiModelProperty(value =  "状态 0:禁用 1:启用" ,allowableValues = "0,1")
    private Integer status;

    @ApiModelProperty(value = "类型")
    private String type;	// 类型


    @ApiModelProperty(hidden = true)
    private String appId;	// app id 支持多产品
    /**
     *  产品包名
     */
    @ApiModelProperty(value = "产品包名")
    private String packageName;


    @ApiModelProperty(value = "上级id")
    private Integer parentId;   //父Id


    public Dict convert() {
        Dict dict = new Dict();
        dict.setId(id);
        dict.setValue(value);
        dict.setLabel(label);
        dict.setStatus(status);
        dict.setType(type);

        dict.setAppId(appId);
        dict.setPackageName(packageName);
        dict.setParentId(parentId);
        return dict;
    }
}
