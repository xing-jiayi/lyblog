package top.crushtj.blog.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.crushtj.blog.common.domain.dos.UserDo;
import top.crushtj.blog.common.domain.mappers.UserMapper;

import java.util.Objects;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/11
 * @description 用户详情
 */

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 模拟从数据库加载用户信息
     *
     * @param username 用户名
     * @return 用户详情
     * @throws UsernameNotFoundException 用户不存在异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDo user = userMapper.findByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        // authorities指定用户角色，暂写死为ADMIN
        return User.withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("ADMIN")
            .build();
    }
}
