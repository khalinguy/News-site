package com.xeonline;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.xeonline.Util.Encode;

public class Test {
	
	public static void main(String[] args) throws Exception{
		/*
		 * URL obj = new URL("http://mkyong.com"); URLConnection conn =
		 * obj.openConnection();
		 * 
		 * //get all headers Map<String, List<String>> map =
		 * conn.getRequestProperties();// getHeaderFields(); for (Map.Entry<String,
		 * List<String>> entry : map.entrySet()) { System.out.println("Key : " +
		 * entry.getKey() + " ;Value : " + entry.getValue()); }
		 * 
		 * //get header by 'key' String server = conn.getHeaderField("Server");
		 */
		System.out.println(Encode.MD5("Khanh Linh"));
	}
}
