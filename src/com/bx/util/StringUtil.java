package com.bx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2016年4月7日 Stringutil.java
 * @author CZP
 * @parameter
 */
public class StringUtil {

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getCurrentDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	public static String formatLike(String str) {
		if (StringUtil.isEmpty(str)) {
			return null;
		} else {
			return "%" + str + "%";
		}

	}

}
