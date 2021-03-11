/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.base.util</p>
 * <p>File:PropertiesUtil.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月9日 下午12:36:07
 */
package com.winner.commons.base.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrBuilder;

import java.io.File;
import java.util.Map;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月9日 下午12:36:07
 * @version 1.0.2021
 */
public class PropertiesUtil {

	public static PropertiesUtil newProperties(final String name) {
		String path = StrBuilder.create(getClasspath(), "/", name, ".properties").toString();
		return new PropertiesUtil(FileUtil.newFile(path));
	}
	
	private static String getClasspath() {
		return Thread.currentThread().getContextClassLoader().getResource("dynamicmodel").getPath();
	}
	
	private PropertiesUtil(File propertiesFile) {
		this.file = propertiesFile;
	}
	
	public void appendLine(final String key, final Object value) {
		String content = StrBuilder.create(key, "=", String.valueOf(value), FileUtil.getLineSeparator()).toString();
		this.file = FileUtil.appendUtf8String(content, file);
	}
	
	public void appendLine(final Map<String, Object> properties) {
		properties.forEach((key, value) -> appendLine(key, value));
	}
	
	private File file;
}
