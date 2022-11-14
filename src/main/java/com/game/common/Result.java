package com.game.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回得对象
 * @param <T>
 */
@Data
@ApiModel(value = "统一返回对象格式")
public class Result<T> {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码，1表示请求操作成功，0表示请求操作失败")
    private Integer code;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "返回的提示信息")
    private String msg;

    /**
     * 传输数据对象
     */
    @ApiModelProperty(value = "返回的数据")
    private T data;

//    操作成功
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 1;
        return result;
    }

//    操作失败
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 0;
        return result;
    }
}
