package com.miniBOM.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result <T> {
    private int code = 0;
    private String msg;
    public T data;
    public T error_data;

    public static Result error(String msg){
        return new Result(1,msg,null,null);
    }

    public static <E> Result<E> error(String msg, E error_data){
        return new Result(1,msg,null,error_data);
    }

    public static Result success(){
        return new Result(0,"SUCCESS",null,null);
    }

    public static <E> Result<E> success(E data){
        return new Result(0,"SUCCESS",data,null);
    }

    //其他逻辑错误
    public static Result otherError(String msg){
        return new Result(2,msg,null,null);
    }
}
