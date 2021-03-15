package com.winner.commons.template.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.IdUtil;
import com.deepoove.poi.config.ConfigureBuilder;
import com.diboot.core.service.impl.BaseServiceImpl;
import com.winner.commons.base.model.FileModel;
import com.winner.commons.template.entity.CommonTemplate;
import com.winner.commons.template.entity.CommonTemplateFile;
import com.winner.commons.template.mapper.CommonTemplateFileMapper;
import com.winner.commons.template.service.CommonTemplateFileService;
import com.winner.commons.word.poitl.model.WordDataModel;
import com.winner.commons.word.poitl.util.WordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * .
 *
 * @Classname CommonTemplateFileServiceImpl
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/4 下午2:52
 */
@Slf4j
@Service
@Transactional
public class CommonTemplateFileServiceImpl extends BaseServiceImpl<CommonTemplateFileMapper, CommonTemplateFile> implements CommonTemplateFileService {

    @Autowired
    private CommonTemplateFileMapper mapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public CommonTemplateFile upload(FileModel fileModel, CommonTemplate template) throws RuntimeException {
        try {
            // 上传文件
            CommonTemplateFile templateFile = template.file(fileModel);
            fileModel.transferTo(templateFile.getTemplatePath());
            // 保存文件信息
            mapper.createTemplateFile(templateFile.setId(IdUtil.simpleUUID()).fixed(fileModel.getClasspath()));

            // 返回模版文件对象
            return templateFile;
        } catch (Throwable e) {
            log.error("template upload is fail: ", e);
            throw new RuntimeException();
        }
    }

    @Override
    public File export(Serializable templateId, WordDataModel dataModel) {
        CommonTemplateFile templateFile = mapper.getTemplateFile(templateId);
        File tmpFile = FileUtil.file(templateFile.getTemplatePath());
        return WordUtil.of(builder).export(tmpFile, dataModel);
    }

    @Override
    public Boolean delete(Serializable id) throws FileNotFoundException {
        CommonTemplateFile templateFile = this.getById(id);
        String classpath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        String templatePath = StrBuilder.create(classpath, templateFile.getTemplatePath()).toString();
        File tmpFile = FileUtil.file(templatePath);
        return this.removeById(id) && tmpFile.exists() && tmpFile.delete();
    }

    @Override
    public void parse(CommonTemplateFile templateFile) throws Exception {
        String classpath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        String templatePath = StrBuilder.create(classpath, templateFile.getTemplatePath()).toString();
        File tmpFile = FileUtil.file(templatePath);
        WordUtil.of(builder).parse(templateFile.getTemplateId(), tmpFile);
    }

    @Override
    public CommonTemplateFileService builder(ConfigureBuilder builder) {
        this.builder = builder;
        return this;
    }

    private ConfigureBuilder builder;

}
