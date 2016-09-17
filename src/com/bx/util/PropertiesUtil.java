package com.bx.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @date 2016年4月7日 PropertiesUtil.java
 * @author CZP
 * @parameter
 */
public class PropertiesUtil {

	public static String getValue(String key) throws Exception {
		Properties properties = new Properties();
		// JAVA里面对于类进行调用配置资源的文件数据，以this.getClass().getResourceAsStream()来读取比较合适。
		InputStream inputStream = new PropertiesUtil().getClass().getResourceAsStream("/diary.properties");
		properties.load(inputStream);
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(PropertiesUtil.getValue("pageSize"));
	}
}
