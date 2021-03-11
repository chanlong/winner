package com.winner.commons.base.model;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.StrBuilder;
import com.winner.commons.base.enums.PolicyType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * .
 *
 * @Classname FileModel
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/8 下午12:46
 */
@Slf4j
@Data
@RequiredArgsConstructor(staticName = "of")
public class FileModel implements Serializable {

    private Long size;
    private byte[] body;
    private String name;
    private PolicyType type;
    private InputStream stream;
    private MultipartFile file;

    public static FileModel of(MultipartFile file) {
        FileModel inst = of();
        try {
            inst.file = file;
            inst.name = file.getOriginalFilename();
            inst.size = file.getSize();
            inst.stream = file.getInputStream();
        } catch (IOException e) {
            log.error("The FileModel set FileInputStream is error: ", e);
        }
        return inst;
    }

    public FileModel policy(PolicyType type) {
        this.type = type;
        return this;
    }

    public void transferTo(String destFilepath) {
        File destFile = FileUtil.file(destFilepath);
        try {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            file.transferTo(destFile);
        } catch (Exception e) {
            log.error("The FileModel invoke transferTo is error: ", e);
        }
    }

    public String getFilename() {
        return type.getValue();
    }

    public String getExtension() {
        return FileNameUtil.extName(file.getOriginalFilename());
    }

    public String getClasspath() {
        try {
            return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        } catch (FileNotFoundException e) {
            return ResourceUtils.CLASSPATH_URL_PREFIX;
        }
    }

    public String getFilepath(String path) {
        return StrBuilder.create(getClasspath(), path, getFilename(), SYMBOL, getExtension()).toString();
    }

    public String getFileurl(String path) {
        return StrBuilder.create(path, getFilename(), SYMBOL, getExtension()).toString();
    }

    public static final String SYMBOL = ".";
}
