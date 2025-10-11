package top.crushtj.blog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/09/23
 * @description JSON工具类
 */

@Slf4j
public class JsonUtil {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    /**
     * 将对象转换为JSON字符串
     *
     * @param obj 需要转换的对象
     * @return JSON字符串，如果转换失败则返回对象的toString()结果
     */
    public static String toJsonString(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }
}
