/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.antilong.antiadmin.core.utils.tool</p>
 * <p>File:ReflectUtil.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年1月7日 上午11:33:31
 */
package com.winner.commons.base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p>描述: 反射工具类</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年1月7日 上午11:33:31
 * @version 1.0.2021
 */
public abstract class ReflectUtil {

	/**
	 * 返回范型的真实类型.
	 * @param clazz
	 * @author chanlong(陈龙)
	 * @date 2021年2月5日 下午12:30:06
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericType(Class<?> clazz) {
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType)type;
		return (Class<T>)ptype.getActualTypeArguments()[0];
	}
	
	/**
	 * 返回范型的真实类型.
	 * @param clazz
	 * @param index
	 * @author chanlong(陈龙)
	 * @date 2021年2月5日 下午12:30:53
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericType(Class<?> clazz, Integer index) {
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType)type;
		return (Class<T>)ptype.getActualTypeArguments()[index];
	}
}
