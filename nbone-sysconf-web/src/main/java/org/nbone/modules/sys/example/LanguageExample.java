package org.nbone.modules.sys.example;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-12-23
 */
@ApiModel
@Getter
public class LanguageExample {

    @ApiModelProperty(example = "阿拉伯文")
    private String ar = "阿拉伯文";

    @ApiModelProperty(example = "克罗地亚文")
    private String hr = "克罗地亚文";

    @ApiModelProperty(example = "法文")
    private String fr = "法文";

    @ApiModelProperty(example = "西班牙文")
    private String es = "西班牙文";
}
