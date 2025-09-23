package top.crushtj.blog.web.model;


import lombok.Data;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/23
 * @description 用户对象
 */
@Data
public class User {

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别，0-未知，1-男，2-女
     */
    private Integer gender;
}
