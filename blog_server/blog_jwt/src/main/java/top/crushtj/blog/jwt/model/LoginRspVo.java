package top.crushtj.blog.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/11
 * @description 登录响应参数类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRspVo {

    /**
     * Token 值
     */
    private String token;
}
