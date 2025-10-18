package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.crushtj.blog.common.domain.dos.BlogSettingsDo;

import javax.validation.constraints.NotBlank;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 21:08
 * @description 博客设置表(t_blog_settings)表数据库访问层

 **/

public interface BlogSettingsMapper extends BaseMapper<BlogSettingsDo> {

    BlogSettingsDo queryByAuthorId(@NotBlank(message = "作者名不能为空") String userId);
}

