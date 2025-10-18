package top.crushtj.blog.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.crushtj.blog.admin.model.vo.settings.SettingsUpdateVo;
import top.crushtj.blog.common.domain.dos.BlogSettingsDo;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 21:43
 * @description 博客设置转换器
 */

@Mapper
public interface BlogSettingsConvert {

    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    BlogSettingsDo convertVo2Do(SettingsUpdateVo settingsUpdateVo);
}
