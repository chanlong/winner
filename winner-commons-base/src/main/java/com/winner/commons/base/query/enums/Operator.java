/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.antilong.antiadmin.base.query.enums</p>
 * <p>File:Operator.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年1月7日 上午10:45:03
 */
package com.winner.commons.base.query.enums;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * <p>描述: 查询操作枚举</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年1月7日 上午10:45:03
 * @version 1.0.2021
 */
public enum Operator {

	/** 模糊 */
	LIKE {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.like(column, val);
		}
	},
	LIKE_LEFT {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.likeLeft(column, val);
		}
	},
	LIKE_RIGHT {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.likeRight(column, val);
		}
	},
	NOT_LIKE {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.notLike(column, val);
		}
	},
	/** 等于 */
	EQ {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.eq(column, val);
		}
	},
	/** 不等于 */
	NE {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.ne(column, val);
		}
	},
	/** 小于 */
	LT {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.lt(column, val);
		}
	},
	/** 小于等于 */
	LE {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.le(column, val);
		}
	},
	/** 大于 */
	GT {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.gt(column, val);
		}
	},
	/** 大于等于 */
	GE {
		@Override
		public<T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.ge(column, val);
		}
	},
	/** 包含 */
	IN {
		@Override
		public<T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			if (ArrayUtil.isArray(val)) {
				Object[] values = (Object[])val;
				wrapper.in(column, values);
			}
		}
	},
	/** 不包含 */
	NOT_IN {
		@Override
		public<T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			if (ArrayUtil.isArray(val)) {
				Object[] values = (Object[])val;
				wrapper.notIn(column, values);
			}
		}
	},
	/** 之间 */
	BETWEEN {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			if (ArrayUtil.isArray(val)) {
				Object[] values = (Object[])val;
				wrapper.between(column, values[0], values[1]);
			}
		}
	},
	/** 不在...之间 */
	NOT_BETWEEN {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			if (ArrayUtil.isArray(val)) {
				Object[] values = (Object[])val;
				wrapper.notBetween(column, values[0], values[1]);
			}
		}
	},
	/** 为空 */
	IS_NULL {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.isNull(column);
		}
	},
	/** 非空 */
	IS_NOT_NULL {
		@Override
		public <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val) {
			wrapper.isNotNull(column);
		}
	};

	public abstract <T> void wrapper(QueryWrapper<T> wrapper, String column, Object val);
}
