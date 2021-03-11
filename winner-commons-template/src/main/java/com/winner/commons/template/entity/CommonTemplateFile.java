package com.winner.commons.template.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * .
 *
 * @Classname AnalysisTemplateFile
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/2/26 上午9:55
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@TableName("tb_com_template_file")
@EqualsAndHashCode(callSuper = false)
public class CommonTemplateFile extends Model<CommonTemplateFile> {

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "模版ID")
    private String templateId;

    @ApiModelProperty(value = "模版文件URL")
    private String templateUrl;

    @ApiModelProperty(value = "模版文件路径")
    private String templatePath;

    @ApiModelProperty(value = "模版文件版本")
    private Integer templateVersion;

    public CommonTemplateFile fixed(String classpath) {
        templatePath = StrUtil.isNotEmpty(templatePath) ? templatePath.replace(classpath, "") : "";
        return this;
    }
}
