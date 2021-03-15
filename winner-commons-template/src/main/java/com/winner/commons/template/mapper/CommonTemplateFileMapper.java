package com.winner.commons.template.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.winner.commons.template.entity.CommonTemplateFile;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * .
 *
 * @Classname AnalysisTemplateFileMapper
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/2/26 上午9:57
 */
public interface CommonTemplateFileMapper extends BaseCrudMapper<CommonTemplateFile> {

    /**
     * 保存模版文件.
     *
     * @description TODO
     * @param templateFile
     * @return void
     * @author chanlong
     * @date 2021/3/4 下午5:26
     */
    Integer createTemplateFile(@Param("file") CommonTemplateFile templateFile);

    /**
     * 模版导出.
     *
     * @description TODO
     * @param templateId
     * @return com.winner.commons.template.entity.CommonTemplateFile
     * @author chanlong
     * @date 2021/3/8 下午5:10
     */
    CommonTemplateFile getTemplateFile(Serializable templateId);
}
