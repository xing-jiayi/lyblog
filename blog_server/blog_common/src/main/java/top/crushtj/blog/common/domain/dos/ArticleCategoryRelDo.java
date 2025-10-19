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
 * @description 文章所属分类关联表(t_article_category_rel)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article_category_rel")
public class ArticleCategoryRelDo implements Serializable {
    private static final long serialVersionUID = 205857696660920838L;

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
     * 分类id
     */
    @TableField("CATEGORY_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 删除标志位：0：未删除 1：已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

}

