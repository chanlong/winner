package com.winner.commons.template.entity;

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
 * @Classname CommonTemplateModel
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/9 下午3:36
 */
@Data
@Accessors(fluent = true)
@RequiredArgsConstructor(staticName = "of")
@TableName("tb_com_template_model")
@EqualsAndHashCode(callSuper = false)
public class CommonTemplateModel extends Model<CommonTemplateModel> {

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "模版标签")
    private String tag;

    @ApiModelProperty(value = "标签类型")
    private String type;

    @ApiModelProperty(value = "模版表达式")
    private String expression;

    @ApiModelProperty(value = "模版ID")
    private String templateId;

    @ApiModelProperty(value = "模版ID")
    private String templateFileId;
}
