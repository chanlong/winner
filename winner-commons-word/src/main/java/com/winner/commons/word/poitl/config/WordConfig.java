/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.conf.properties</p>
 * <p>File:GlobalConfigProperties.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月7日 上午11:49:51
 */
package com.winner.commons.word.poitl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月7日 上午11:49:51
 * @version 1.0.2021
 */
@Component
@ConfigurationProperties(prefix = "com.winner.common")
public class WordConfig {
	
	public static WordProperties word;
	
	public void setWord(WordProperties word) {
		WordConfig.word = word;
	}
	
	public WordProperties getWord() {
		return word;
	}
}
