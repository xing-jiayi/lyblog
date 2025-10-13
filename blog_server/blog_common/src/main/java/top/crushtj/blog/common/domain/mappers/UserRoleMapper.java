package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.crushtj.blog.common.domain.dos.UserRoleDo;

import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/13 14:58
 * @description 用户角色表(t_user_role)表数据库访问层
 **/

public interface UserRoleMapper extends BaseMapper<UserRoleDo> {

    List<UserRoleDo> selectByUserId(@Param("userId") Long userId);
}

