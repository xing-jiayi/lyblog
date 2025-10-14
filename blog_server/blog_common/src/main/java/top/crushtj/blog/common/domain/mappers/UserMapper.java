package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.crushtj.blog.common.domain.dos.UserDo;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/09 19:45
 * @description 用户表(t_user)表数据库访问层
 **/

public interface UserMapper extends BaseMapper<UserDo> {

    UserDo selectByUsername(@Param("username") String username);

    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);

    ;

}

