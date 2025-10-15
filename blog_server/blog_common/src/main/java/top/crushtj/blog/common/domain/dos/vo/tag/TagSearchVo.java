package top.crushtj.blog.common.domain.dos.vo.tag;

import lombok.*;
import top.crushtj.blog.common.model.BasePageQuery;

import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/15 18:09
 * @description 标签查询参数
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagSearchVo extends BasePageQuery {
    private static final long serialVersionUID = 2281405210273216584L;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 创建时间-开始
     */
    private LocalDateTime createTimeStart;

    /**
     * 创建时间-结束
     */
    private LocalDateTime createTimeEnd;

}
