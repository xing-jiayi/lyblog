package top.crushtj.blog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/09
 * @description MybatisPlus配置类
 */

@Configuration
@MapperScan("top.crushtj.**.mappers")
public class MybatisPlusConfig {
}
