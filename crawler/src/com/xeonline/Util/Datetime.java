package com.xeonline.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.xeonline.Const;

public class Datetime {
	
	static Date date= new Date();
	static Calendar cal = Calendar.getInstance();

	public static String getDay() throws Exception{
		cal.setTime(date);
		return (cal.get(Calendar.DATE)>9?"":"0")+cal.get(Calendar.DATE);
	}
	
	public static String getMonth() throws Exception{
		cal.setTime(date);
		return (cal.get(Calendar.MONTH)>9?"":"0")+cal.get(Calendar.MONTH);
	}
	
	public static String getYear() throws Exception{
		cal.setTime(date);
		return (cal.get(Calendar.YEAR)>9?"":"0")+cal.get(Calendar.YEAR);
	}
	
	public static String getDOW() throws Exception{
		cal.setTime(date);
		return Const.DOW[cal.get(Calendar.DAY_OF_WEEK)];
	}
	/*
	 * String to Date convert
	 * DoW: 		EEE
	 * Day: 		dd 
	 * Month: 		MMM 
	 * Year:		yyyy 
	 * HH:			hour
	 * Minute:		mm
	 * Secord:		ss 
	 * Timezone:	ZZZ
	 */
	public static Date toDate(String dateString, String formatDate) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		//format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date d = format.parse(dateString);
		return d;
	}
	
	public static void main(String[] args) throws Exception {}
}
