package top.crushtj.blog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 21:08
 * @description 博客设置表(t_blog_settings)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_blog_settings")
public class BlogSettingsDo implements Serializable {
    private static final long serialVersionUID = -75535637412900793L;

    /**
     * 设置id
     */
    @TableId("SETTING_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long settingId;

    /**
     * 博客Logo
     */
    @TableField("LOGO")
    private String logo;

    /**
     * 博客名称
     */
    @TableField("BLOG_NAME")
    private String blogName;

    /**
     * 作者名
     */
    @TableField("AUTHOR")
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

