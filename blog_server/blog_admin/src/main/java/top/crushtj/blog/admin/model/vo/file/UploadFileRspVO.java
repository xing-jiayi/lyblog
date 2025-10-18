package top.crushtj.blog.admin.model.vo.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 20:47
 * @description 文件上传响应参数类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFileRspVO {

    private String url;
}
