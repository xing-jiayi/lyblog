package top.crushtj.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.crushtj.blog.common.exception.BaseExceptionInterface;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/25
 * @description 异常码枚举类
 */
@AllArgsConstructor
@Getter
public enum ResponseCodeEnum implements BaseExceptionInterface {

    SYSTEM_ERROR("500", "系统异常，请稍后重试"),
    PARAMS_ERROR("400", "参数异常，请检查后重试"),
    PARAMS_NOT_VALID("401", "参数校验失败，请检查后重试"),
    ;

    private final String errorCode;
    private final String errorMessage;

}
