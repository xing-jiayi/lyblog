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

    SYSTEM_ERROR("500", "系统异常，请稍后重试！"),
    PARAMS_ERROR("400", "参数异常，请检查后重试！"),
    PARAMS_NOT_VALID("401", "参数校验失败，请检查后重试！"),
    PARAM_NULL("504", "请求参数不能为空"),

    // 500xx 用户/权限相关
    LOGIN_FAIL("50000", "登录失败！"),
    USERNAME_OR_PWD_ERROR("50001", "用户名或密码错误！"),
    UNAUTHORIZED("50002", "无访问权限，请先登录！"),
    FORBIDDEN("50003", "当前用户没有访问权限！"),
    USERNAME_NOT_FOUND("50004", "用户名不存在！"),
    USER_ADD_FAILURE("50005", "添加用户失败！"),
    SAME_PASSWORD("50006", "新密码和旧密码不能相同！"),

    // 300xx 文章/分类/标签相关
    CATEGORY_NAME_IS_EXISTED("30001", "分类名称已存在！"),
    CATEGORY_IS_NOT_EXISTED("30002", "分类不存在！"),
    TAG_NAME_IS_EXISTED("30011", "标签名称已存在！"),
    TAG_IS_NOT_EXIST("30012", "标签不存在！"),

    // 400xx 文件相关
    FILE_UPLOAD_ERROR("40001", "文件上传失败！");

    private final String errorCode;
    private final String errorMessage;

}
