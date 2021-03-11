package com.winner.commons.template.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @Classname CommonTemplateConfiguration
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/4 下午3:04
 */
@Component
@ConfigurationProperties(prefix = "com.winner.common.template")
public class TemplateConfig {

    public static String uploadPath;
    public static String downloadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String path) {
        TemplateConfig.uploadPath = path;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String path) {
        TemplateConfig.downloadPath = path;
    }
}
