package top.crushtj.blog.admin.services;

import top.crushtj.blog.admin.model.vo.user.UpdateAdminPasswordVo;
import top.crushtj.blog.admin.model.vo.user.UserVo;
import top.crushtj.blog.common.domain.dos.UserDo;
import top.crushtj.blog.common.utils.Response;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 11:08
 * @description 管理员用户service
 */

public interface UserService {

    /**
     * 修改管理员密码
     *
     * @param updateAdminPasswordVo 修改管理员密码VO
     * @return 执行结果
     */
    Response updatePasswordByUserName(UpdateAdminPasswordVo updateAdminPasswordVo);

    /**
     * 新增用户
     *
     * @param userDo 用户DO
     * @return 执行结果
     */
    Response<UserVo> addUser(UserDo userDo);

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    Response<UserVo> getCurrentUserInfo();
}
