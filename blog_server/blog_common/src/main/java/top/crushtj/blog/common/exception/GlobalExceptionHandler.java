package top.crushtj.blog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.utils.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/25
 * @description 全局异常处理类
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常
     * @param request 请求
     * @param bizException 业务异常
     * @return 响应结果
     */
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest request, BizException bizException) {
        log.warn("{} request error: errorCode={}, errorMessage={}", request.getRequestURI(),
            bizException.getErrorCode(), bizException.getErrorMessage());
        return Response.failure(bizException);
    }

    /**
     * 其他异常
     * @param request 请求
     * @param exception 异常
     * @return 响应结果
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Response<Object> handleOtherException(HttpServletRequest request, Exception exception) {
        log.error("{} request error, ", request.getRequestURI(), exception);
        return Response.failure(ResponseCodeEnum.SYSTEM_ERROR);
    }
}
