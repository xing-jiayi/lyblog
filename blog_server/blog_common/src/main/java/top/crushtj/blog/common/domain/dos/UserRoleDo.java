package top.crushtj.blog.common.domain.dos;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/13 14:58
 * @description 用户角色表(t_user_role)实体类
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_role")
public class UserRoleDo implements Serializable {
    private static final long serialVersionUID = -50543178631223288L;

    /**
     * 角色id
     */
    @TableId("ROLE_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 角色名
     */
    @TableField("ROLE_NAME")
    private String roleName;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

}

