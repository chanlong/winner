/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.conf.properties</p>
 * <p>File:GlobalConfigWordProperties.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月21日 下午5:24:14
 */
package com.winner.commons.word.poitl.config;

import lombok.Data;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月21日 下午5:24:14
 * @version 1.0.2021
 */
@Data
public class WordProperties {

	private String exportPath;
	private String gramerPrefix = "{{";
	private String gramerSuffix = "}}";
}
