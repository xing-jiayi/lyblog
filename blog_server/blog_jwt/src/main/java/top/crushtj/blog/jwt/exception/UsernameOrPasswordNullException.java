package top.crushtj.blog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/11
 * @description 用户名或密码为空异常
 */

public class UsernameOrPasswordNullException extends AuthenticationException {
    private static final long serialVersionUID = -2299659298639887421L;

    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
