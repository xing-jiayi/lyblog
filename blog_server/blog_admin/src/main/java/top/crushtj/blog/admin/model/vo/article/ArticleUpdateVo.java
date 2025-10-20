package top.crushtj.blog.admin.model.vo.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 14:53
 * @description 文章更新类
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateVo extends ArticleVo {
    private static final long serialVersionUID = 6337919738228338968L;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;

    /**
     * 文章封面
     */
    @ApiModelProperty(value = "文章封面")
    private String cover;

    /**
     * 文章摘要
     */
    @ApiModelProperty(value = "文章摘要")
    private String summary;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime publishTime;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签列表")
    private List<String> tagNames;
}
