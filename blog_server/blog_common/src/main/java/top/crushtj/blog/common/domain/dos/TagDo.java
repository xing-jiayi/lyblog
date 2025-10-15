package top.crushtj.blog.common.domain.dos;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/15 14:27
 * @description 文章标签表(t_tag)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_tag")
public class TagDo implements Serializable {
    private static final long serialVersionUID = 949708588118079360L;

    /**
     * 标签id
     */
    @TableId("TAG_ID")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long tagId;

    /**
     * 标签名称
     */
    @TableField("TAG_NAME")
    @NotBlank(message = "标签名称不能为空")
    private String tagName;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 最后一次更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志位：0：未删除 1：已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

}

