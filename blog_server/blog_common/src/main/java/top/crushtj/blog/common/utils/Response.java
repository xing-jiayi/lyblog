package top.crushtj.blog.common.utils;


import lombok.Data;

import java.io.Serializable;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/25
 * @description 响应工具类
 */
@Data
public class Response<T> implements Serializable {

    /**
     * 是否成功 true-成功，false-失败
     */
    private Boolean success = true;

    /**
     * 响应信息
     */
    private String errorMessage;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应
     * @return Response<T>
     * @param <T> 数据类型
     */
    public static <T> Response<T> success() {
        return new Response<>();
    }

    /**
     * 成功响应
     * @param data 响应数据
     * @return Response<T>
     * @param <T> 数据类型
     */
    public static <T> Response<T> success(T data) {
        Response<T> res = new Response<>();
        res.setData(data);
        return res;
    }

    public static <T>Response<T> failure(){
        Response<T> res = new Response<>();
        res.setSuccess(false);
        return res;
    }

    public static <T> Response<T> failure(String errorMessage){
        Response<T> res = new Response<>();
        res.setSuccess(false);
        res.setErrorMessage(errorMessage);
        return res;
    }

    public static <T> Response<T> failure(String errorCode,String errorMessage){
        Response<T> res = new Response<>();
        res.setSuccess(false);
        res.setErrorMessage(errorMessage);
        res.setErrorCode(errorCode);
        return res;
    }
}
