/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.common.entity</p>
 * <p>File:BaseEntity.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月5日 上午11:14:27
 */
package com.winner.commons.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.diboot.core.config.Cons;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.ContextHelper;
import com.diboot.core.util.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winner.commons.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * .
 *
 * @Classname BaseEntity
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/2/24 下午2:34
 */
@Data
@ApiModel("实体抽象类")
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity<E extends Model<E>> extends Model<E> implements BaseVO<E> {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "主键ID", hidden = true)
	private String id;
	
	/** 0-正常 9-冻结 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "状态标识,0:正常,9:冻结", hidden = true, position = 100)
	private Integer status;

	/** 删除标记 */
	@TableLogic(value = "0", delval = "1")
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "删除标识,0:正常,1:已删除", hidden = true, position = 101)
	private Integer deleted;

	@Version
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "版本（乐观锁）", hidden = true, position = 102)
	private Integer version;
	
	/** 创建者 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建者", hidden = true, position = 103)
	private String createUser;
	
	/** 更新者 */
	@TableField(fill = FieldFill.UPDATE)
	@ApiModelProperty(value = "更新者", hidden = true, position = 104)
	private String updateUser;

	/** 创建时间 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建时间", hidden = true, position = 105)
	private LocalDateTime createTime;
	
	/** 更新时间 */
	@TableField(fill = FieldFill.UPDATE)
	@ApiModelProperty(value = "更新时间", hidden = true, position = 106)
	private LocalDateTime updateTime;

	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {
		String jsonStr = JSON.stringify(this);
		return JSON.toMap(jsonStr);
	}

	@JsonIgnore
	public Object getPrimaryKeyVal() {
		String pk = ContextHelper.getPrimaryKey(this.getClass());
		return Cons.FieldName.id.name().equals(pk) ? this.getId() : BeanUtils.getProperty(this, pk);
	}
}
