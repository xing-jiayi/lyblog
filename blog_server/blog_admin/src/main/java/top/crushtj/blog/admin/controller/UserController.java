package top.crushtj.blog.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.admin.model.vo.user.UpdateAdminPasswordVo;
import top.crushtj.blog.admin.model.vo.user.UserVo;
import top.crushtj.blog.admin.services.UserService;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.domain.dos.UserDo;
import top.crushtj.blog.common.utils.Response;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 11:16
 * @description 管理员用户controller
 */

@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 修改管理员密码
     *
     * @param updateAdminPasswordVo 修改管理员密码VO
     * @return 执行结果
     */
    @PostMapping("/updPasswd")
    @ApiOperationLog(description = "修改管理员密码")
    @ApiOperation(value = "修改管理员密码")
    public Response updatePasswordByUserName(@RequestBody @Validated UpdateAdminPasswordVo updateAdminPasswordVo) {
        return userService.updatePasswordByUserName(updateAdminPasswordVo);
    }

    @PostMapping("/addUser")
    @ApiOperationLog(description = "新增用户")
    @ApiOperation(value = "新增用户")
    public Response<UserVo> addUser(@RequestBody @Validated UserDo userDo) {
        return userService.addUser(userDo);
    }

    @PostMapping("/getUserInfo")
    @ApiOperationLog(description = "获取当前登录用户信息")
    @ApiOperation(value = "获取当前登录用户信息")
    public Response<UserVo> getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }
}
