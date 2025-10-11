package top.crushtj.blog.jwt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.utils.Response;
import top.crushtj.blog.jwt.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/11
 * @description 未登录访问受保护资源处理器
 */

@Component
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        log.warn("用户未登录访问受保护的资源: ", authException);
        if (authException instanceof InsufficientAuthenticationException) {
            ResultUtil.failure(response, HttpStatus.UNAUTHORIZED.value(),
                Response.failure(ResponseCodeEnum.UNAUTHORIZED));
            return;
        }

        ResultUtil.failure(response, HttpStatus.UNAUTHORIZED.value(), Response.failure(authException.getMessage()));
    }
}
