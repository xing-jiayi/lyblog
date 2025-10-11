package top.crushtj.blog.common.utils;

import lombok.Data;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.exception.BizException;

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
     *
     * @param <T> 数据类型
     * @return Response<T>
     */
    public static <T> Response<T> success() {
        return success(null);
    }

    /**
     * 成功响应
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return Response<T>
     */
    public static <T> Response<T> success(T data) {
        Response<T> res = new Response<>();
        res.setData(data);
        return res;
    }

    /**
     * 失败响应
     *
     * @param <T> 数据类型
     * @return Response<T>
     */
    public static <T> Response<T> failure() {
        Response<T> res = new Response<>();
        res.setSuccess(false);
        return res;
    }

    /**
     * 失败响应
     *
     * @param errorMessage 错误信息
     * @param <T>          数据类型
     * @return Response<T>
     */
    public static <T> Response<T> failure(String errorMessage) {
        return failure(null, errorMessage);
    }

    /**
     * 失败响应
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @param <T>          数据类型
     * @return Response<T>
     */
    public static <T> Response<T> failure(String errorCode, String errorMessage) {
        Response<T> res = new Response<>();
        res.setSuccess(false);
        res.setErrorMessage(errorMessage);
        res.setErrorCode(errorCode);
        return res;
    }

    /**
     * 失败响应
     *
     * @param bizException 业务异常
     * @param <T>          数据类型
     * @return Response<T>
     */
    public static <T> Response<T> failure(BizException bizException) {
        return failure(bizException.getErrorCode(), bizException.getErrorMessage());
    }

    public static <T> Response<T> failure(ResponseCodeEnum responseCodeEnum) {
        return failure(responseCodeEnum.getErrorCode(), responseCodeEnum.getErrorMessage());
    }
}
