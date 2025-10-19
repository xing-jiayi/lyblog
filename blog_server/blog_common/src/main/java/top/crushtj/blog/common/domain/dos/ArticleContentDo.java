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
 * @date 2025/10/19 12:44
 * @description 文章内容表(t_article_content)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article_content")
public class ArticleContentDo implements Serializable {
    private static final long serialVersionUID = -72847263575352194L;

    /**
     * 文章内容id
     */
    @TableId("CONTENT_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long contentId;

    /**
     * 文章id
     */
    @TableField("ARTICLE_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    /**
     * 文章正文
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 删除标志位：0：未删除 1：已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

}

