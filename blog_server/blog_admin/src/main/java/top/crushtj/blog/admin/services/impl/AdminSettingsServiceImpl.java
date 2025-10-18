package top.crushtj.blog.admin.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.crushtj.blog.admin.convert.BlogSettingsConvert;
import top.crushtj.blog.admin.model.vo.settings.SettingsUpdateVo;
import top.crushtj.blog.admin.services.AdminSettingsService;
import top.crushtj.blog.common.domain.dos.BlogSettingsDo;
import top.crushtj.blog.common.domain.mappers.BlogSettingsMapper;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.exception.BizException;
import top.crushtj.blog.common.utils.IdGenerator;
import top.crushtj.blog.common.utils.Response;

import java.util.Objects;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 21:15
 * @description 博客设置Service实现类
 */

@Slf4j
@Service
public class AdminSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDo>
    implements AdminSettingsService {

    private final BlogSettingsMapper settingsMapper;

    public AdminSettingsServiceImpl(BlogSettingsMapper settingsMapper) {
        this.settingsMapper = settingsMapper;
    }

    @Override
    public Response<BlogSettingsDo> getDetail(String settingId) {
        if (settingId == null || settingId.isEmpty()) {
            throw new BizException(ResponseCodeEnum.PARAM_NULL);
        }
        BlogSettingsDo settingsDo = settingsMapper.selectById(settingId);
        if (Objects.isNull(settingsDo)) {
            return Response.failure("查询的博客信息不存在");
        }
        return Response.success(settingsDo);
    }

    @Override
    public Response<BlogSettingsDo> updateSettings(SettingsUpdateVo settingsUpdateVo) {
        IdGenerator idGenerator = IdGenerator.getInstance();
        long id = 0L;
        BlogSettingsDo settingsDo = BlogSettingsConvert.INSTANCE.convertVo2Do(settingsUpdateVo);
        // 使用构建器模式创建BlogSettingsDo对象，现已改为使用MapStruct进行转换
        // BlogSettingsDo settingsDo = BlogSettingsDo.builder()
        //     .logo(settingsUpdateVo.getLogo())
        //     .blogName(settingsUpdateVo.getBlogName())
        //     .author(settingsUpdateVo.getAuthor())
        //     .introduction(settingsUpdateVo.getIntroduction())
        //     .avatar(settingsUpdateVo.getAvatar())
        //     .giteeHomepage(settingsUpdateVo.getGiteeHomepage())
        //     .csdnHomepage(settingsUpdateVo.getCsdnHomepage())
        //     .giteeHomepage(settingsUpdateVo.getGiteeHomepage())
        //     .customUrl(settingsUpdateVo.getCustomUrl())
        //     .build();
        BlogSettingsDo settings = settingsMapper.queryByAuthor(settingsUpdateVo.getAuthor());
        if (Objects.isNull(settings)) {
            id = idGenerator.nextId();
        } else {
            id = settings.getSettingId();
        }
        settingsDo.setSettingId(id);
        saveOrUpdate(settingsDo);
        return Response.success(settingsDo);
    }
}
