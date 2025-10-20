package top.crushtj.blog.common.domain.dos.vo.tag;

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
 * @date 2025/10/19 13:57
 * @description 文章列表标签Vo
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagVo implements Serializable {
    private static final long serialVersionUID = 4059630483193889322L;

    /**
     * 标签id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    /**
     * 标签名称
     */
    private String tagName;
}
