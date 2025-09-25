package top.crushtj.blog.common.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/25
 * @description 业务异常
 */
@Getter
@Setter
public class BizException extends RuntimeException{
    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    public BizException(BaseExceptionInterface baseException){
        this.errorCode = baseException.getErrorCode();
        this.errorMessage = baseException.getErrorMessage();
    }
}
