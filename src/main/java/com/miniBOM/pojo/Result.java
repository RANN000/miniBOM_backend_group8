package com.miniBOM.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    public T data;

    public static <E> Result success(E data) {
        return new Result<>(0,"操作成功",data);
    }

    public static Result success() {
        return new Result(0,"操作成功",null);
    }

    public static Result error(String msg) {
        return new Result(1,msg,null);
    }
}
