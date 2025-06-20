package com.miniBOM.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result <T> {
    private int code = 0;
    @JsonProperty("result")
    private String msg;
    @JsonProperty("data")
    public T data;
    @JsonProperty("errors")
    public T error_data;

    //错误返回code：1，msg：错误信息
    public static Result error(String msg){
        return new Result(1,msg,null,null);
    }

    //错误返回msg和error_data
    public static <E> Result<E> error(String msg, E error_data){
        return new Result(1,msg,null,error_data);
    }

    //无返回data
    public static Result success(){
        return new Result(0,"SUCCESS",null,null);
    }

    //正常返回data
    public static <E> Result<E> success(E data){
        return new Result(0,"SUCCESS",data,null);
    }

    //其他逻辑错误
    public static Result otherError(String msg){
        return new Result(2,msg,null,null);
    }
}
