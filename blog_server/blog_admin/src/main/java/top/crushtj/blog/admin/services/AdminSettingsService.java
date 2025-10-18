package top.crushtj.blog.admin.services;

import top.crushtj.blog.admin.model.vo.settings.SettingsUpdateVo;
import top.crushtj.blog.common.domain.dos.BlogSettingsDo;
import top.crushtj.blog.common.utils.Response;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 21:14
 * @description 博客设置service
 */

public interface AdminSettingsService {

    Response<BlogSettingsDo> updateSettings(SettingsUpdateVo settingsUpdateVo);

    Response<BlogSettingsDo> getDetail(String userId);
}
