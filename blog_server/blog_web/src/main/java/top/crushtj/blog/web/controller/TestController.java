package top.crushtj.blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.domain.dos.UserDo;
import top.crushtj.blog.common.domain.mappers.UserMapper;
import top.crushtj.blog.common.utils.JsonUtil;
import top.crushtj.blog.common.utils.Response;
import top.crushtj.blog.web.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/23
 * @description 测试Controller
 */

@RestController
@Slf4j
@Api(tags = "测试接口")
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation("测试接口")
    public User test(@RequestBody User user) {
        log.error("232323");
        log.warn("232323");
        log.info("232323");
        log.debug("232323");
        return user;
    }

    @PostMapping("/test/validation")
    @ApiOperationLog(description = "校验测试接口")
    @ApiOperation("校验测试接口")
    public Response<User> testVa(@RequestBody @Validated User user) {
        // 是否存在校验错误
        //if (bindingResult.hasErrors()) {
        //    // 获取校验不通过字段的提示信息
        //    String errorMsg = bindingResult.getFieldErrors()
        //            .stream()
        //            .map(FieldError::getDefaultMessage)
        //            .collect(Collectors.joining(", "));
        //
        //    return Response.failure(errorMsg);
        //}

        log.info(JsonUtil.toJsonString(user));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setUpdateTime(LocalTime.now());
        // 返参
        return Response.success(user);
    }

    @PostMapping("/test/exception")
    @ApiOperationLog(description = "异常测试接口")
    @ApiOperation("异常测试接口")
    public Response<String> testException(@RequestBody @Validated User user, BindingResult bindingResult) {
        //throw new BizException(ResponseCodeEnum.SYSTEM_ERROR);
        int i = 1 / 0;
        return Response.success();
    }

    @PostMapping("/admin/test")
    @ApiOperationLog(description = "鉴权测试接口")
    @ApiOperation("鉴权测试接口")
    public Response<User> testAdmin(@RequestBody @Validated User user) {
        log.info(JsonUtil.toJsonString(user));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setUpdateTime(LocalTime.now());
        // 返参
        return Response.success(user);
    }

    @PostMapping("/user/add")
    @ApiOperationLog(description = "用户新增接口")
    @ApiOperation("用户新增接口")
    public Response<UserDo> addUser(@RequestBody @Validated UserDo user) {
        log.info(JsonUtil.toJsonString(user));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
        // 返参
        return Response.success(user);
    }

    @PostMapping("/admin/update")
    @ApiOperationLog(description = "测试更新接口")
    @ApiOperation(value = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response testUpdate() {
        log.info("更新成功...");
        return Response.success();
    }

}
