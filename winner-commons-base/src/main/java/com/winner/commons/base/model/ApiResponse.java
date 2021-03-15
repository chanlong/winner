package com.winner.commons.base.model;

import cn.hutool.http.HttpStatus;
import com.diboot.core.vo.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 *
 * @Classname ApiResponse
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/8 下午3:08
 */
@Data
@ApiModel("API统一响应模型")
@RequiredArgsConstructor(staticName = "of")
public class ApiResponse implements Serializable {

    @ApiModelProperty(value = "额外信息", position = 7)
    private Map<String, Object> additional = new HashMap<>();

    @ApiModelProperty(value = "数据指纹", position = 6)
    private String fingerprint;

    @ApiModelProperty(value = "数字签名", position = 5)
    private String signature;

    @ApiModelProperty(value = "响应消息", position = 3)
    private String message;

    @ApiModelProperty(value = "响应数据", position = 4)
    private Object data;

    @ApiModelProperty(value = "状态码", position = 1)
    private int status;

    {
        this.message = "接口调用成功！";
    }

    protected ApiResponse(Object data, int status, String message, String signature, Map<String, Object> additional, String fingerprint) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.signature = signature;
        this.additional = additional;
        this.fingerprint = fingerprint;
    }

    public static ApiResponse of(String message) {
        return of().message(message);
    }

    public static ApiResponse ok() {
        ApiResponse inst = of();
        inst.status = HttpStatus.HTTP_OK;
        return inst;
    }

    public static ApiResponse error(Exception exception) {
        ApiResponse inst = of();
        inst.additional.put("error", exception.getLocalizedMessage());
        inst.additional.put("stackTrace", exception.getStackTrace()[0]);
        inst.status = HttpStatus.HTTP_INTERNAL_ERROR;
        return inst.message("接口调用失败！");
    }

    public ApiResponse message(String message) {
        this.message = message;
        return this;
    }

    public ApiResponse error() {
        this.status = HttpStatus.HTTP_INTERNAL_ERROR;
        return this;
    }

    public ApiResponse data(Object data) {
        this.data = data;
        return this;
    }

    public ApiResponse page(Pagination pagination) {
        return new PagingApiResult(this, pagination);
    }

    /**
     * 数据指纹.
     *
     * @description TODO
     * @param fingerprint 数据指纹
     * @return com.winner.commons.base.model.ApiResponse
     * @author chanlong
     * @date 2021/3/8 下午3:56
     */
    public ApiResponse fingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    /**
     * 签名字串.
     *
     * @description TODO
     * @param signature 签名
     * @return com.winner.commons.base.model.ApiResponse
     * @author chanlong
     * @date 2021/3/8 下午3:56
     */
    public ApiResponse signature(String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * 额外信息.
     *
     * @description TODO
     * @param key key
     * @param info info
     * @return com.winner.commons.base.model.ApiResponse
     * @author chanlong
     * @date 2021/3/8 下午3:57
     */
    public ApiResponse additional(String key, Object info) {
        this.additional.put(key, info);
        return this;
    }
}
