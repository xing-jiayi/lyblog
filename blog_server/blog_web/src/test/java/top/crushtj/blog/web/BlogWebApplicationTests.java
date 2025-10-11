package top.crushtj.blog.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.crushtj.blog.common.domain.dos.UserDo;
import top.crushtj.blog.common.domain.mappers.UserMapper;

import java.time.LocalDateTime;

@SpringBootTest
class BlogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        // 构建数据库实体类
        UserDo userDO = UserDo.builder()
            .username("xingjiayi")
            .password("$2a$10$Aa.Aeu1XDt/HDX4lsLRWwuKxHqlBgYFaoI3Ry8bLEr3XRq4PQuURW")
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .isDeleted(0)
            .build();

        userMapper.insert(userDO);
    }

}
