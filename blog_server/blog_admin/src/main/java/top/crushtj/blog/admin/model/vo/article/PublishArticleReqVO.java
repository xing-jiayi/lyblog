package top.crushtj.blog.admin.model.vo.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 00:44
 * @description 文章发布请求参数类
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章发布请求参数类")
public class PublishArticleReqVO implements Serializable {
    private static final long serialVersionUID = -1556462545157190576L;

    /**
     * 文章id
     */
    @TableId("ARTICLE_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    /**
     * 文章正文
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 文章标题
     */
    @TableField("TITLE")
    @NotBlank(message = "文章标题不能为空")
    @Length(min = 1, max = 40, message = "文章标题字数需大于 1 小于 40")
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
     * 发布时间
     */
    @TableField("PUBLISH_TIME")
    private LocalDateTime publishTime;

    /**
     * 分类id
     */
    // @NotEmpty(message = "文章分类不能为空")
    private List<String> categoryId;

    /**
     * 标签id
     */
    // @NotEmpty(message = "文章标签不能为空")
    private List<String> tagNames;
}
