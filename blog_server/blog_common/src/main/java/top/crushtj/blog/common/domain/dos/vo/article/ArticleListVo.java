package top.crushtj.blog.common.domain.dos.vo.article;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 13:05
 * @description 文章列表Vo
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo extends ArticleVo {
    private static final long serialVersionUID = 8547180810578796143L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 浏览量
     */
    private Long readNum;

}
