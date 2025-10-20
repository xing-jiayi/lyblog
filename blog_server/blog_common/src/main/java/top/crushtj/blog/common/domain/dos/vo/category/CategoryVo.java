package top.crushtj.blog.common.domain.dos.vo.category;

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
 * @date 2025/10/19 13:56
 * @description 文章列表分类Vo
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = 2613949835122562934L;

    /**
     * 分类id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 分类名称
     */
    private String name;
}
