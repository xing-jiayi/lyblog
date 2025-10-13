package top.crushtj.blog.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.crushtj.blog.common.domain.dos.UserDo;
import top.crushtj.blog.common.domain.dos.UserRoleDo;
import top.crushtj.blog.common.domain.mappers.UserMapper;
import top.crushtj.blog.common.domain.mappers.UserRoleMapper;

import java.util.List;
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

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 模拟从数据库加载用户信息
     *
     * @param username 用户名
     * @return 用户详情
     * @throws UsernameNotFoundException 用户不存在异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDo user = userMapper.selectByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        String[] roleArr = null;
        List<UserRoleDo> roles = userRoleMapper.selectByUserId(user.getUserId());

        if (roles != null && !roles.isEmpty()) {
            roleArr = roles.stream()
                .map(UserRoleDo::getRoleName)
                .toArray(String[]::new);
        }

        // authorities指定用户角色
        if (roleArr != null) {
            return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(roleArr)
                .build();
        }
        return User.withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("ROLE_USER") // 默认角色
            .build();
    }
}
