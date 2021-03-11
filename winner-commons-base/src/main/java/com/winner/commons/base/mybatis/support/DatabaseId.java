/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.antilong.antiadmin.datasource.properties</p>
 * <p>File:DatabaseProperties.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2020年12月28日 下午12:07:45
 */
package com.winner.commons.base.mybatis.support;

import java.util.Properties;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2020年12月28日 下午12:07:45
 * @version 1.0.2020
 */
public class DatabaseId {

	public static Properties getProperties() {
		Properties p = new Properties();
		p.setProperty("DM", "dm");
		p.setProperty("MySQL", "mysql");
		p.setProperty("Oracle", "oracle");
		p.setProperty("Sqlserver", "sqlserver");
		p.setProperty("Postgresql", "postgresql");
		return p;
	}
	
}
