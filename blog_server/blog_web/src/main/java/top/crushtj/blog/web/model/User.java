package top.crushtj.blog.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/23
 * @description 用户对象
 */
@Data
@ApiModel("用户对象")
public class User {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 性别，0-未知，1-男，2-女
     */
    @NotNull(message = "性别不能为空")
    @ApiModelProperty("性别，0-未知，1-男，2-女")
    private Integer gender;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "年龄必须大于等于18岁")
    @Max(value = 120, message = "年龄必须小于等于120岁")
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    @NotNull(message = "更新日期不能为空")
    private LocalDate updateDate;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @NotNull(message = "更新时间不能为空")
    private LocalTime updateTime;
}
