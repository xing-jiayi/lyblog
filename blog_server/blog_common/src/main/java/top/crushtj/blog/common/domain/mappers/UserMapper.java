package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.crushtj.blog.common.domain.dos.UserDo;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/09 19:45
 * @description 用户表(t_user)表数据库访问层
 **/

public interface UserMapper extends BaseMapper<UserDo> {

    default UserDo findByUsername(String username) {
        LambdaQueryWrapper<UserDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDo::getUsername, username);
        return this.selectOne(wrapper);
    }

    ;

}

