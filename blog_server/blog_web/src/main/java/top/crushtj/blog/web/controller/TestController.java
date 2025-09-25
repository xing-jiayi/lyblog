package top.crushtj.blog.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.utils.Response;
import top.crushtj.blog.web.model.User;

import java.util.stream.Collectors;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/23
 * @description 测试Controller
 */
@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User test(@RequestBody User user) {
        log.error("232323");
        log.warn("232323");
        log.info("232323");
        log.debug("232323");
        return user;
    }

    @PostMapping("/testValida")
    @ApiOperationLog(description = "测试接口")
    public Response<String> test(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 是否存在校验错误
        if (bindingResult.hasErrors()) {
            // 获取校验不通过字段的提示信息
            String errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return Response.failure(errorMsg);
        }

        // 返参
        return Response.success();
    }
}
