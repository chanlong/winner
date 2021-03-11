package com.winner.commons.base.enums;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * 随机字符串生成策略.
 *
 * @Classname PolicyType
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/4 下午4:06
 */
public enum PolicyType {
    /** UUID */
    UUID {
        @Override
        public String getValue() {
            return IdUtil.simpleUUID();
        }
    },
    /** 默认 */
    NORMAL {
        @Override
        public String getValue() {
            return "";
        }
    },
    /** 时间值 */
    DATETIME {
        @Override
        public String getValue() {
            return DateTime.now().toString(DatePattern.PURE_DATETIME_MS_FORMAT);
        }
    },
    /** 时间戳 */
    TIMESTAMP {
        @Override
        public String getValue() {
            return String.valueOf(DateTime.now().getTime());
        }
    },
    /** 雪花ID */
    SNOWFLAKE {
        @Override
        public String getValue() {
            return IdUtil.getSnowflake(1,1).nextIdStr();
        }
    },
    /** 日期+随机数 */
    DATERANDOM {
        @Override
        public String getValue() {
            return DateTime.now().toString(DatePattern.PURE_DATE_FORMAT) + RandomUtil.randomNumbers(4);
        }
    };

    public abstract String getValue();

}
