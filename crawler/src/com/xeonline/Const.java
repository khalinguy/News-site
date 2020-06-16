package com.xeonline;

import java.util.HashMap;
import java.util.Map;

public class Const {
	public final static String 		IMAGE_FOLDER 	= "D:\\eclipse\\data-crawler\\imgs\\";

	public final static String[] 	DOW 		= { "Thứ Bảy","Chủ Nhật", "Thứ Hai","Thứ Ba","Thứ Tư","Thứ Năm","Thứ Sáu"};
	public final static String[] 	MOY 		= { "Tháng Một","Tháng Hai","Tháng Ba","Tháng Tư","Tháng Năm","Tháng Sáu", "Tháng Bảy","Tháng Tám","Tháng Chín","Tháng Mười","Tháng Mười Một","Tháng Mười Hai",};
	public final static String		DATE_FORMAT = "";
			
	public final static String		USER_AGENT 	= "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
	public final static String		SERVER_REF 	= "https://www.google.com";
	
	@SuppressWarnings("serial")
	static final Map<String , String> FUNCTIONS = new HashMap<String , String>(){
		{
		    put("https://vnexpress.net",	"getExpress");
		    put("https://zingnews.vn",		"getZing");
		    //put("https://www.24h.com.vn",	"get24h");
		    //put("https://vietnamnet.vn",	"getVietnamNet");
		}
	};
}