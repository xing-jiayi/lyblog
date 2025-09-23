package top.crushtj.blog.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.web.model.User;

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
}
