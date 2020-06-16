package com.xeonline.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	public static String query = ""; 
	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Connection getConn(String url, String port, String database, String user, String password) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection(	 "jdbc:mysql://"+url+":"+port+"/"+database
				        										+"?user="+user
				        										+"&password="+password
				        										+"&useSSL=false");
        	if(conn!=null) {
        		System.out.println("Connected!");
        		return conn;
        	}
        } catch(Exception e) {
        	e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * 
	 * @param conn
	 * @throws Exception
	 */
	public static void closeConn(Connection conn) throws Exception{
		try {
			conn.close();
			System.out.println(conn.toString()+" closed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet execQuery(Connection conn, String query) throws Exception {
		Statement stat = null;
		try {
			stat = conn.createStatement();
			return stat.executeQuery(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param conn
	 * @param query
	 * @param params
	 * @return
	 */
	public static int execUpdate(Connection conn, String query, String[] params) {
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			for(int i=0; i<params.length; i++) {
				ps.setString(i+1, params[i]);
			}
			return ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 
	 * @param conn
	 * @param query
	 * @return
	 */
	public static int execUpdate(Connection conn, String query) {
		try {
			Statement stat = conn.createStatement();
			return stat.executeUpdate(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 
	 * @param conn
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public static boolean checkExist(Connection conn, String query) throws Exception {
		ResultSet rs = execQuery(conn, query);
		return (rs.next()?false:true);
	}
}
