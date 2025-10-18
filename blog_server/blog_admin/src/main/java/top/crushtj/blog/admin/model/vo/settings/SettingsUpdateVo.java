package top.crushtj.blog.admin.model.vo.settings;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 21:11
 * @description 博客信息修改对象类
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SettingsUpdateVo", description = "博客信息修改对象类")
public class SettingsUpdateVo {

    /**
     * 设置id
     */
    @TableId("SETTING_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long settingId;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 博客Logo
     */
    @TableField("LOGO")
    @NotBlank(message = "博客Logo不能为空")
    private String logo;

    /**
     * 博客名称
     */
    @TableField("BLOG_NAME")
    @NotBlank(message = "博客名称不能为空")
    private String blogName;

    /**
     * 作者名
     */
    @TableField("AUTHOR")
    @NotBlank(message = "作者名不能为空")
    private String author;

    /**
     * 介绍语
     */
    @TableField("INTRODUCTION")
    private String introduction;

    /**
     * 作者头像
     */
    @TableField("AVATAR")
    @NotBlank(message = "作者头像不能为空")
    private String avatar;

    /**
     * GitHub 主页访问地址
     */
    @TableField("GITHUB_HOMEPAGE")
    private String githubHomepage;

    /**
     * CSDN 主页访问地址
     */
    @TableField("CSDN_HOMEPAGE")
    private String csdnHomepage;

    /**
     * Gitee 主页访问地址
     */
    @TableField("GITEE_HOMEPAGE")
    private String giteeHomepage;

    /**
     * 知乎主页访问地址
     */
    @TableField("ZHIHU_HOMEPAGE")
    private String zhihuHomepage;

    /**
     * 自定义链接
     */
    @TableField("CUSTOM_URL")
    private String customUrl;
}
