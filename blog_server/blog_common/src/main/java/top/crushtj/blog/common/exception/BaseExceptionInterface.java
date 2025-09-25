package top.crushtj.blog.common.exception;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/25
 * @description 基础异常接口
 */
public interface BaseExceptionInterface {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    String getErrorCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getErrorMessage();
}
