package com.game.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 比赛
 */
@Data
@ApiModel(value = "比赛信息对象")
public class Game {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "id，主键自增", example = "1", position = 1, required = true)
    private Long id;

    /**
     * 赛事名称
     */
    @ApiModelProperty(value = "赛事名称", example = "篮球赛", position = 2, required = true)
    private String gameName;

    /**
     * 主办单位
     */
    @ApiModelProperty(value = "赛事主办单位", example = "XXX单位", position = 3)
    private String organizer;

    /**
     * 赛事举办地点
     */
    @ApiModelProperty(value = "赛事举办地点", example = "操场", position = 4, required = true)
    private String place;

    /**
     * 报名状态
     */
    @ApiModelProperty(value = "是否结束报名, 1表示报名中，0表示报名已结束", example = "1", position = 5, required = true)
    private Integer status;

    /**
     * 赛事文件
     */
    @ApiModelProperty(value = "赛事文件地址", example = "文件存放路径", position = 6)
    private String gameFile;
}
