package com.example.shirodemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class HttpResult<T> {

    private int code = 200;
    private String msg;
    private T data;

    public HttpResult(int code){
        this.code = code;
    }

    public HttpResult(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public HttpResult(int code, T data){
        this.code = code;
        this.data = data;
    }

    public static <T> HttpResult<T> ok(){
        return new HttpResult<T>(HttpStatus.SC_OK);
    }

    public static <T> HttpResult<T> ok(String msg){
        return new HttpResult<T>(HttpStatus.SC_OK, msg);
    }

    public static <T> HttpResult<T> ok(T data){
        return new HttpResult<T>(HttpStatus.SC_OK, data);
    }

    public static <T> HttpResult<T> ok(String msg, T data){
        return new HttpResult<T>(HttpStatus.SC_OK, msg, data);
    }

    public static <T> HttpResult<T> error(){
        return new HttpResult<T>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知错误");
    }

    public static <T> HttpResult<T> error(String msg){
        return new HttpResult<T>(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static <T> HttpResult<T> error(int code, String msg, T data){
        return new HttpResult<T>(code, msg, data);
    }

    public static <T> HttpResult<T> error(int code, String msg){
        return new HttpResult<T>(code, msg);
    }
}
