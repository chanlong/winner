/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.antilong.antiadmin.base.query.annotation</p>
 * <p>File:Query.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年1月7日 上午11:03:26
 */
package com.winner.commons.base.query.annotation;

import com.winner.commons.base.query.enums.Operator;

import java.lang.annotation.*;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年1月7日 上午11:03:26
 * @version 1.0.2021
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Query {

	Operator value() default Operator.EQ;
}
