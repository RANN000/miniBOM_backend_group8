package com.miniBOM.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private int code;
    private String msg;
    public T data;
    //操作成功返回对象
    public static <E> Result success(E data) {
        return new Result<>(0,"操作成功",data);
    }
    //操作成功无返回
    public static Result success() {
        return new Result(0,"操作成功",null);
    }
    //操作失败
    public static Result error(String msg) {
        return new Result(1,msg,null);
    }

    //其他逻辑错误
    public static Result otherError(String msg){
        return new Result(2,msg,null);
    }
}
