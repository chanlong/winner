/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.antilong.antiadmin.base.model</p>
 * <p>File:BaseQuery.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年1月7日 上午10:36:45
 */
package com.winner.commons.base.query;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.winner.commons.base.query.annotation.Query;
import lombok.Data;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年1月7日 上午10:36:45
 * @version 1.0.2021
 */
@Data
public abstract class BaseQuery<E extends Model<E>> implements Serializable {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	public Wrapper<E> createQuery() {
		Class<?> beanClass = this.getClass();
		List<Field> fieldList = FieldUtils.getFieldsListWithAnnotation(beanClass, Query.class);
		
		QueryWrapper<E> queryWrapper = fieldList.stream().reduce(Wrappers.query(), (wrapper, field) -> {
			Query query = AnnotationUtil.getAnnotation(field, Query.class);
			try {
				String name = field.getName();
				Object value = BeanUtil.getProperty(this, name);
				if (ObjectUtil.isNotNull(value) && ObjectUtil.isNotEmpty(value)) {
					query.value().wrapper(wrapper, StrUtil.toUnderlineCase(name), value);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			return wrapper;
		}, (l, r) -> l);

		return queryWrapper;
	}

}
