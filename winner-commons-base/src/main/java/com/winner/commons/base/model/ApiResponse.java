package com.winner.commons.base.model;

import cn.hutool.http.HttpStatus;
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
@RequiredArgsConstructor(staticName = "of")
public class ApiResponse implements Serializable {

    private Map<String, Object> additional = new HashMap<>();
    private String fingerprint;
    private String signature;
    private String message;
    private Object data;
    private int status;

    {
        this.message = "接口调用成功！";
    }

    public static ApiResponse of(String message) {
        ApiResponse inst = of();
        inst.message = message;
        return inst;
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
        inst.message = "接口调用失败！";
        return inst;
    }

    public ApiResponse error() {
        this.status = HttpStatus.HTTP_INTERNAL_ERROR;
        return this;
    }

    public ApiResponse data(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 数据指纹.
     *
     * @description TODO
     * @param fingerprint
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
     * @param signature
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
     * @param key
     * @param info
     * @return com.winner.commons.base.model.ApiResponse
     * @author chanlong
     * @date 2021/3/8 下午3:57
     */
    public ApiResponse additional(String key, Object info) {
        this.additional.put(key, info);
        return this;
    }
}
