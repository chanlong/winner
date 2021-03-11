package com.winner.commons.template.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.winner.commons.base.entity.BaseEntity;
import com.winner.commons.base.model.FileModel;
import com.winner.commons.template.config.TemplateConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * .
 *
 * @Classname Template
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/2/24 下午2:30
 */
@Data
@ApiModel
@TableName("tb_com_template")
@EqualsAndHashCode(callSuper = false)
public class CommonTemplate extends BaseEntity<CommonTemplate> {

    @ApiModelProperty(value = "模版类型", position = 1)
    private String type;

    @ApiModelProperty(value = "模版名称", position = 2)
    private String name;

    @ApiModelProperty(value = "模版描述", position = 3)
    private String description;

    @ApiModelProperty(value = "业务ID", position = 4)
    private String businessKey;

    public CommonTemplateFile file(FileModel fileModel) {
        String filepath = fileModel.getFilepath(TemplateConfig.uploadPath);
        String fileurl = fileModel.getFileurl(TemplateConfig.downloadPath);
        return CommonTemplateFile.of().setTemplateId(getId()).setTemplateUrl(fileurl).setTemplatePath(filepath);
    }
}
