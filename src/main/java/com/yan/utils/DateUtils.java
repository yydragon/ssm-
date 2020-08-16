package com.yan.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	//日期转换成字符串
	public static String date2String(java.util.Date departureTime ,String patt) {
		SimpleDateFormat sdf = new SimpleDateFormat(patt);
		String format = sdf.format(departureTime);
		return format;
	}
	
	//字符串转换成日期
	public static Date string2Date(String str,String patt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(patt);
		Date parse = (Date) sdf.parse(str);
		return parse;
	}
}
