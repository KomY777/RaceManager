package com.game.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "学生参赛数据对象")
public class Contestant {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "id，主键自增",example = "1",position = 1,required = true)
    private long id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",example = "1",position = 2)
    private String name;
    /**
     * 学号
     */
    @ApiModelProperty(value = "学号",example = "15115",position = 3)
    private String idNumber;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", example = "1" ,position =4)
    private int sex;
    /**
     * 学院
     */
    @ApiModelProperty(value= "学院", example = "计算机学院",position = 5)
    private String academy;
    /**
     * 专业
     */
    @ApiModelProperty(value = "专业",example = "软件工程",position = 6)
    private String major;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号",example ="10086",position = 7)
    private String phone;
    /**
     * 竞赛名
     */
    @ApiModelProperty(value = "竞赛名",example = "5165",position = 8)
    private String gameName;
}
