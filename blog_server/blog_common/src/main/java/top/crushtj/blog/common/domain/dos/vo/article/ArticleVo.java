package top.crushtj.blog.common.domain.dos.vo.article;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.crushtj.blog.common.domain.dos.vo.category.CategoryVo;
import top.crushtj.blog.common.domain.dos.vo.tag.TagVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 14:31
 * @description 文章基础Vo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo implements Serializable {
    private static final long serialVersionUID = -4057587181465255254L;

    /**
     * 文章id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 分类
     */
    private List<CategoryVo> categories;

    /**
     * 标签
     */
    private List<TagVo> tags;
}
