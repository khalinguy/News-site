package com.xeonline;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xeonline.Util.Database;

@WebServlet("/getnews")
public class News extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public News() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "";
		try{
			//Connection conn = Database.getConn("103.57.222.148", "3306", "xol", "xol", "B@ochau123");
			Connection conn = Database.getConn("localhost", "3306", "car", "root", "root");
			ResultSet rs = Database.execQuery(conn, "SELECT id,title,header,link,source FROM content WHERE header!='' ORDER BY publicAt DESC LIMIT 100");
			ResultSetMetaData rsmd = rs.getMetaData();
			data += "[";
			while(rs.next()){
				data+="{";
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++ ) {
				  String item = "\""+rsmd.getColumnName(i) + "\":\"" + rs.getString(rsmd.getColumnName(i))+"\""+(i<rsmd.getColumnCount()?",":"");
				  data += item;
				}
				data += "}"+(!rs.isLast()?",":"");
			}
			data +="]";
			rs.close();
			Database.closeConn(conn);
		}catch(Exception ex){}
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
		response.getWriter().append(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
