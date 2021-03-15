package com.winner.commons.base.model;

import com.diboot.core.vo.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * .
 *
 * @Classname PagingApiResult
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/15 下午3:45
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
public class PagingApiResult extends ApiResponse {

    @ApiModelProperty(value = "分页信息", position = 2)
    private Pagination page;

    public PagingApiResult(ApiResponse response, Pagination page) {
        super(response.getData(), response.getStatus(), response.getMessage(), response.getSignature(), response.getAdditional(), response.getFingerprint());
        this.page = page;
    }
}
