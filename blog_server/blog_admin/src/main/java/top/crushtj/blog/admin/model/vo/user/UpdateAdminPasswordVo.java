package top.crushtj.blog.admin.model.vo.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 11:03
 * @description 修改管理员密码Vo
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "修改用户密码 VO")
public class UpdateAdminPasswordVo implements Serializable {
    private static final long serialVersionUID = -8935243038997056399L;

    @ApiModelProperty(value = "用户ID")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
