package com.miniBOM.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result <T> {
    private int code = 0;
    private String result;
    public T data;
    public T errors=null;

    public static Result error(String result){
        return new Result(1,result,null,null);
    }

    public static <E> Result<E> error(String result, E errors){
        return new Result(1,result,null,errors);
    }

    public static Result success(){
        return new Result(200,"SUCCESS",null,null);
    }

    public static Result success(String result){
        return new Result(200,result,null,null);
    }

    public static <E> Result<E> success(String result,E data){
        return new Result(200,result,data,null);
    }

    public static <E> Result<E> success(E data){
        return new Result(200,"SUCCESS",data,null);
    }

    //其他逻辑错误
    public static Result otherError(String result){
        return new Result(2,result,null,null);
    }
}
