package top.crushtj.blog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/25
 * @description Knife4j配置类
 */

@Configuration
@EnableSwagger2WebMvc
@Profile("dev") // 仅在 dev 环境下开启
public class Knife4jConfig {

    @Bean("webApi")
    public Docket createApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo())
            // 分组名称
            .groupName("Web 前台接口")
            .select()
            // 这里指定 Controller 扫描包路径
            .apis(RequestHandlerSelectors.basePackage("top.crushtj.blog.web.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder().title("lyblog 博客前台接口文档") // 标题
            .description("lyblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。") // 描述
            .termsOfServiceUrl("https://www.crushtj.top/") // API 服务条款
            .contact(new Contact("刑加一", "https://www.crushtj.top", "2294931964@qq.com")) // 联系人
            .version("1.0") // 版本号
            .build();
    }
}
