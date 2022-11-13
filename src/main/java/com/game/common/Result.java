package com.game.common;

import lombok.Data;

/**
 * 统一返回得对象
 * @param <T>
 */
@Data
public class Result<T> {

    private Integer code; //1表示操作成功，0表示操作失败

    private String msg; //提示信息

    private T data; //传输得数据对象

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
