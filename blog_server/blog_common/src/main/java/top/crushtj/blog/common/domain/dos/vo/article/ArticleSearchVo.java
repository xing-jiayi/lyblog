package top.crushtj.blog.common.domain.dos.vo.article;

import lombok.*;
import top.crushtj.blog.common.model.BasePageQuery;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 12:58
 * @description 文章列表查询参数类
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSearchVo extends BasePageQuery {

    private static final long serialVersionUID = 5659617846161987636L;
    /**
     * 文章标题
     */
    private String title;

    /**
     * 创建时间
     */
    private LocalDateTime createTimeStart;

    /**
     * 创建时间
     */
    private LocalDateTime createTimeEnd;

    /**
     * 发布时间
     */
    private LocalDateTime publishTimeStart;

    /**
     * 发布时间
     */
    private LocalDateTime publishTimeEnd;

    /**
     * 分类ID
     */
    private List<String> categoryId;

    /**
     * 标签ID
     */
    private List<String> tagId;
}
