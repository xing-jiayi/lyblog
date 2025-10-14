package top.crushtj.blog.common.domain.dos.vo.category;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.crushtj.blog.common.model.BasePageQuery;

import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 15:35
 * @description 分类查询参数
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorySearchVo extends BasePageQuery {

    private static final long serialVersionUID = -6164718825884391721L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long categoryId;

    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTimeStart;

    /**
     * 最后一次更新时间
     */
    private LocalDateTime createTimeEnd;
}
