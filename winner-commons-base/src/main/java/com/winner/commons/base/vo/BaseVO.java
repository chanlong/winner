package com.winner.commons.base.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.diboot.core.util.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winner.commons.base.util.ReflectUtil;

import java.io.Serializable;

/**
 * .
 *
 * @Classname BaseVO
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/15 下午12:16
 */
public interface BaseVO<E extends Model<E>> extends Serializable {

    @SuppressWarnings("unchecked")
    @JsonIgnore
    default E getEntity() {
        Class<E> entityClass = ReflectUtil.getGenericType(this.getClass());
        E entityBean = cn.hutool.core.util.ReflectUtil.newInstance(entityClass);
        return (E) BeanUtils.copyProperties(this, entityBean);
    }
}
