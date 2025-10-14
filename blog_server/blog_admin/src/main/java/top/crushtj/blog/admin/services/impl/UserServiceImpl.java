package top.crushtj.blog.admin.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.crushtj.blog.admin.model.vo.user.UpdateAdminPasswordVo;
import top.crushtj.blog.admin.model.vo.user.UserVo;
import top.crushtj.blog.admin.services.UserService;
import top.crushtj.blog.common.domain.dos.UserDo;
import top.crushtj.blog.common.domain.mappers.UserMapper;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.utils.IdGenerator;
import top.crushtj.blog.common.utils.Response;

import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 11:11
 * @description 用户serviceImpl
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 修改管理员密码
     *
     * @param updateAdminPasswordVo 修改管理员密码VO
     * @return 执行结果
     */
    @Override
    public Response updatePasswordByUserName(UpdateAdminPasswordVo updateAdminPasswordVo) {
        String username = updateAdminPasswordVo.getUsername();
        String password = updateAdminPasswordVo.getPassword();
        UserDo userDo = userMapper.selectByUsername(username);
        if (passwordEncoder.matches(password, userDo.getPassword())) {
            return Response.failure(ResponseCodeEnum.SAME_PASSWORD);
        }
        String encodedPassword = passwordEncoder.encode(password);
        int updateCount = userMapper.updatePasswordByUsername(username, encodedPassword);
        return updateCount == 1 ? Response.success() : Response.failure(ResponseCodeEnum.USERNAME_NOT_FOUND);
    }

    /**
     * 新增用户
     *
     * @param userDo 用户DO
     * @return 执行结果
     */
    @Override
    public Response<UserVo> addUser(UserDo userDo) {
        userDo.setPassword(passwordEncoder.encode(userDo.getPassword()));
        userDo.setCreateTime(LocalDateTime.now());
        userDo.setUpdateTime(LocalDateTime.now());
        IdGenerator idGenerator = IdGenerator.getInstance();
        userDo.setUserId(idGenerator.nextId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDo, userVo);
        int addCount = userMapper.insert(userDo);
        return addCount == 1 ? Response.success(userVo) : Response.failure(ResponseCodeEnum.USER_ADD_FAILURE);
    }

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @Override
    public Response<UserVo> getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
        String userName = authentication.getName();
        UserDo userDo = userMapper.selectByUsername(userName);
        if (userDo != null) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userDo, userVo);
            return Response.success(userVo);
        }
        return null;
    }
}
