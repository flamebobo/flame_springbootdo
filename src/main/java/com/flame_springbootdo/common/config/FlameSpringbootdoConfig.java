package com.flame_springbootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Flame
 * @Date 2018/9/30 16:23
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "springbootdo")
public class FlameSpringbootdoConfig {
    //上传路径
    private String uploadPath;

    public String getUploadPath(){
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
