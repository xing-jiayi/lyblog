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
import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 12:43
 * @description 文章表(t_article)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article")
public class ArticleDo implements Serializable {
    private static final long serialVersionUID = 863729567021771898L;

    /**
     * 文章id
     */
    @TableId("ARTICLE_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    /**
     * 文章标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 文章封面
     */
    @TableField("COVER")
    private String cover;

    /**
     * 文章摘要
     */
    @TableField("SUMMARY")
    private String summary;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 发布时间
     */
    @TableField("PUBLISH_TIME")
    private LocalDateTime publishTime;

    /**
     * 最后一次更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 删除标志位：0：未删除 1：已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

    /**
     * 被阅读次数
     */
    @TableField("READ_NUM")
    private Integer readNum;

}

