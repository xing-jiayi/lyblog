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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/09 19:45
 * @description 用户表(t_user)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class UserDo implements Serializable {
    private static final long serialVersionUID = -65002962939490958L;

    /**
     * id
     */
    @TableId("ID")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

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
     * 逻辑删除：0：未删除 1：已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

}

