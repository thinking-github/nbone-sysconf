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
public class CountryExample {

    @ApiModelProperty(name = "AE", example = "阿拉伯联合酋长国")
    private String AE;

    @ApiModelProperty(name = "JO", example = "约旦")
    private String JO;

    @ApiModelProperty(name = "SY", example = "叙利亚")
    private String SY;

    @ApiModelProperty(name = "HR", example = "克罗地亚")
    private String HR;

    @ApiModelProperty(name = "BE", example = "比利时")
    private String BE;


}
