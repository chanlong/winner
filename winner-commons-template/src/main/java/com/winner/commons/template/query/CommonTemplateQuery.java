package com.winner.commons.template.query;

import com.winner.commons.base.query.BaseQuery;
import com.winner.commons.base.query.annotation.Query;
import com.winner.commons.base.query.enums.Operator;
import com.winner.commons.template.entity.CommonTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * .
 *
 * @Classname CommonTemplateQuery
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/8 下午3:35
 */
@Data
@ApiModel("查询参数模型")
@EqualsAndHashCode(callSuper = true)
public class CommonTemplateQuery extends BaseQuery<CommonTemplate> {

    @Query(Operator.EQ)
    @ApiModelProperty(value = "模版ID")
    private String id;

    @Query(Operator.LIKE)
    @ApiModelProperty(value = "模版名称")
    private String name;

    @Query(Operator.EQ)
    @ApiModelProperty(value = "模版类型")
    private String type;

    @Query(Operator.EQ)
    @ApiModelProperty(value = "模版状态")
    private Integer status;

    @Query(Operator.EQ)
    @ApiModelProperty(value = "删除标识")
    private Integer deleted;

    @Query(Operator.EQ)
    @ApiModelProperty(value = "业务ID")
    private String businessKey;

}
