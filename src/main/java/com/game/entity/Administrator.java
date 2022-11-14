package com.game.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 管理员
 */
@Data
@ApiModel(value = "标签基本信息数据传输对象")
public class Administrator {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "id，主键自增",example = "1",position = 1,required = true)
    private Long id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号",example = "1",position = 2)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",example = "1",position = 3)
    private String password;
}
