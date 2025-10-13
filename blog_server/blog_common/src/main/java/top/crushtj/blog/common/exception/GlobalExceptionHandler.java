package top.crushtj.blog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.utils.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
     *
     * @param request      请求
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
     *
     * @param request   请求
     * @param exception 异常
     * @return 响应结果
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Response<Object> handleOtherException(HttpServletRequest request, Exception exception) {
        log.error("{} request error, ", request.getRequestURI(), exception);
        return Response.failure(ResponseCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 参数校验异常
     *
     * @param request   请求
     * @param exception 参数校验异常
     * @return 响应结果
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Response<Object> handleMethodArgumentNotValidException(HttpServletRequest request,
        MethodArgumentNotValidException exception) {
        String errorCode = ResponseCodeEnum.PARAMS_NOT_VALID.getErrorCode();
        BindingResult bindingResult = exception.getBindingResult();
        StringBuffer sb = new StringBuffer();
        Optional.of(bindingResult.getFieldErrors())
            .ifPresent(errors -> errors.forEach(error -> {
                sb.append(error.getField())
                    .append(" ")
                    .append(error.getDefaultMessage())
                    .append(", 当前值: '")
                    .append(error.getRejectedValue())
                    .append("'; ");
            }));
        String errorMessage = sb.toString();
        log.warn("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMessage);
        return Response.failure(errorCode, errorMessage);
    }

    /**
     * 捕获 AccessDeniedException 异常，交给 RestAccessDeniedHandler 处理
     *
     * @param e AccessDeniedException
     * @throws AccessDeniedException 鉴权失败异常
     */
    @ExceptionHandler({ AccessDeniedException.class })
    public void throwAccessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        // 捕获到鉴权失败异常，主动抛出，交给 RestAccessDeniedHandler 去处理
        log.info("============= 捕获到 AccessDeniedException =============");
        throw e;
    }
}
