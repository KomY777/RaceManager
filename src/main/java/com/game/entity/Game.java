package com.game.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 比赛
 */
@Data
public class Game {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 赛事名称
     */
    private String gameName;

    /**
     * 主办单位
     */
    private String organizer;

    /**
     * 赛事举办地点
     */
    private String place;

    /**
     * 报名状态
     */
    private Integer status;

    /**
     * 赛事文件
     */
    private String gameFile;
}
