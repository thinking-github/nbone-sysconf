package org.nbone.modules.sys.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nbone.modules.sys.entity.Group;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-12-26
 */
@Data
public class GroupQuery {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value =  "状态 0:禁用 1:启用" ,allowableValues = "0,1")
    private Integer status;

    @ApiModelProperty(hidden = true)
    private String appId;
    /**
     * 产品包名
     */
    @ApiModelProperty(value = "产品包名")
    private String packageName;

    @ApiModelProperty(value = "上级id")
    private Integer parentId;


    public Group convert() {
        Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setStatus(status);
        group.setAppId(appId);
        group.setPackageName(packageName);
        group.setParentId(parentId);
        return group;
    }

}
