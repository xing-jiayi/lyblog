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
 * @date 2025/10/19 00:42
 * @description 文章对应标签关联表(t_article_tag_rel)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article_tag_rel")
public class ArticleTagRelDo implements Serializable {
    private static final long serialVersionUID = -81125422756702895L;

    /**
     * id
     */
    @TableId("ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文章id
     */
    @TableField("ARTICLE_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    /**
     * 标签id
     */
    @TableField("TAG_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

}

