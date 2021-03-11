package com.winner.commons.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepoove.poi.config.ConfigureBuilder;
import com.winner.commons.base.model.FileModel;
import com.winner.commons.template.entity.CommonTemplate;
import com.winner.commons.template.entity.CommonTemplateFile;
import com.winner.commons.word.poitl.model.WordDataModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * .
 *
 * @Classname AnalysisTemplateFileService
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/2/26 上午9:57
 */
public interface CommonTemplateFileService extends IService<CommonTemplateFile> {

    CommonTemplateFile upload(FileModel fileModel, CommonTemplate template) throws Exception;

    CommonTemplateFileService builder(ConfigureBuilder builder);

    File export(Serializable templateId, WordDataModel dataModel);

    void parse(CommonTemplateFile templateFile) throws Exception;

    Boolean delete(Serializable id) throws FileNotFoundException;
}
