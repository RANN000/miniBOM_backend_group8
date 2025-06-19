package com.miniBOM.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result <T> {
    private String msg;
    public T data;
    public T error_data;

    public static Result error(String msg){
        return new Result(msg,null,null);
    }

    public static <E> Result<E> error(String msg, E error_data){
        return new Result(msg,null,error_data);
    }

    public static Result success(){
        return new Result("SUCCESS",null,null);
    }

    public static <E> Result<E> success(E data){
        return new Result("SUCCESS",data,null);
    }

    //其他逻辑错误
    public static Result otherError(String msg){
        return new Result(msg,null,null);
    }
}
