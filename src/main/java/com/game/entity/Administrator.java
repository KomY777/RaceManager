package com.game.entity;


import lombok.Data;

/**
 * 管理员
 */
@Data
public class Administrator {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
