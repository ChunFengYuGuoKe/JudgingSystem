package org.scu.judgingsystem.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    public static final int SUCCESS = 1;
    public static final int FAIL = 1;

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    public Result() {
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result result = new Result();
        result.code = code;
        result.msg = message;
        return result;
    }
}
