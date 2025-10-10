package top.crushtj.blog.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/10
 * @description Security配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .mvcMatchers("/blog_server/admin/**")
            .authenticated() // 认证所有以 /admin 为前缀的 URL 资源
            .anyRequest()
            .permitAll()
            .and() // 其他都需要放行，无需认证
            .formLogin()
            .and() // 使用表单登录
            .httpBasic(); // 使用 HTTP Basic 认证
    }
}
