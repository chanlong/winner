package com.winner.commons.word.poitl.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * .
 *
 * @Classname ExpressionModel
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/10 上午9:12
 */
@Data
@Accessors(fluent = true)
@RequiredArgsConstructor(staticName = "of")
public class ExpressionModel implements Serializable {

    /** 标签类型 */
    private String type;

    /** 标签名称 */
    private String name;

    /** 标签表达式 */
    private String expression;
}
