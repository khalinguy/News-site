package com.xeonline;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.xeonline.Util.Check;
import com.xeonline.Util.Database;
import com.xeonline.Util.Datetime;

public class Crawl {
	
	private static final SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static Document getDoc(String url) throws Exception{
		return 	Jsoup.connect(url)
					.userAgent(Const.USER_AGENT)
					.referrer(Const.SERVER_REF)
					.timeout(5000)
					.get();
	}
	
	private static List<Info> getInfoList(String keyword) {
		List<Info> infoRet = new ArrayList<>();
		String url 		= "https://news.google.com/rss/search";
		String q		= "?q="+keyword;
		String hl 		= "&hl=vi";
		String gl 		= "&gl=VN";
		String ceid 	= "&ceid=VN:vi";
		String link		= url+q+hl+gl+ceid;
		try {
		Document doc 	= getDoc(link);
		//System.out.println(doc.toString());
		Elements elementList = doc.getElementsByTag("item");
		for(Element e : elementList) {
			Info i = new Info();
			i.setGuid(e.getElementsByTag("guid").text());
			i.setLink(e.getElementsByTag("link").text());
			i.setSource(e.select("source").attr("url"));
			i.setPubdate(Datetime.toDate(e.getElementsByTag("pubDate").text(), "EE, dd MMM yyyy HH:mm:ss ZZZ"));	//Fri, 27 Dec 2019 23:04:30 GMT
			infoRet.add(i);
		}
		return infoRet;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static News getKenh14(String url){
		try {
			Document doc = 	getDoc(url);
			//System.out.println(doc.toString());
			News retNews = new News();
			if(doc!=null) {
				Elements title = doc.getElementsByClass("kbwc-title");
				Elements summary = doc.getElementsByClass("knc-sapo");
				Elements body = doc.select("knc-content");
				Elements head =  body.select("img");
				String imgSrc = (head.first().attr("src").indexOf(".jpg")>0 || head.first().attr("src").indexOf(".png")>0?head.first().attr("src"):"");
			
				URL imgurl=new URL(imgSrc);
				BufferedImage image = ImageIO.read(imgurl);
				boolean horizontal = (image.getHeight()<(image.getWidth()*0.8)?true:false);
			
				if(Check.isString(title.text()) && Check.isString(body.text()) && horizontal) {
					String content = Jsoup.clean(body.toString(), Whitelist.relaxed());
					retNews.setTitle(title.text());
					retNews.setHeader(imgSrc);
					retNews.setSummary(summary.text());
					retNews.setContent(content);
					return retNews;
				}else return null;
			}else {
				System.out.println(sdf.format(System.currentTimeMillis())+"getDoc("+url+")=null");
			}
		}catch (Exception e) {
			System.out.println(sdf.format(System.currentTimeMillis())+"getExpress('"+url+"') => "+e.getMessage());
		}
		return null;
	}
	
	public static News getExpress(String url){
		try {
			Document doc = 	getDoc(url);
			//System.out.println(doc.toString());
			News retNews = new News();
			if(doc!=null) {
				Elements title = doc.getElementsByClass("title-detail");
				Elements summary = doc.getElementsByClass("description");
				Elements body = doc.getElementsByClass("fck-detail");
				Elements head =  body.select("img");
				String imgSrc = (head.first().attr("src").indexOf(".jpg")>0 || head.first().attr("src").indexOf(".png")>0?head.first().attr("src"):"");
	
				URL imgurl=new URL(imgSrc);
				BufferedImage image = ImageIO.read(imgurl);
				boolean horizontal = (image.getHeight()<(image.getWidth()*0.8)?true:false);
			
				if(Check.isString(title.text()) && Check.isString(body.text()) && horizontal) {
					String content = Jsoup.clean(body.toString(), Whitelist.relaxed());
					retNews.setTitle(title.text());
					retNews.setHeader(imgSrc);
					retNews.setSummary(summary.text());
					retNews.setContent(content);
					return retNews;
				}else return null;
			}else {
				System.out.println(sdf.format(System.currentTimeMillis())+"getDoc("+url+")=null");
			}
		}catch (Exception e) {
			System.out.println(sdf.format(System.currentTimeMillis())+"getExpress('"+url+"') => "+e.getMessage());
		}
		return null;
	}

	public static News getZing(String url) {
		try {
			Document doc = 	getDoc(url);
			//System.out.println(doc.toString());
			News retNews = new News();
			if(doc!=null) {
				Elements title = doc.getElementsByClass("the-article-title");
				Elements summary = doc.getElementsByClass("the-article-summary");
				Elements body = doc.getElementsByClass("the-article-body");
				Elements head =  body.select("img");
				String imgSrc = (head.first().attr("src").indexOf(".jpg")>0 || head.first().attr("src").indexOf(".png")>0?head.first().attr("src"):"");
				
				URL imgurl=new URL(imgSrc);
				BufferedImage image = ImageIO.read(imgurl);
				boolean horizontal = (image.getHeight()<(image.getWidth()*0.8)?true:false);
				
				if(Check.isString(title.text()) /*&& Check.isString(body.text())*/ && horizontal) {
					String content = Jsoup.clean(body.toString(), Whitelist.relaxed());
					retNews.setTitle(title.text());
					retNews.setHeader(imgSrc);
					retNews.setSummary(summary.text());
					retNews.setContent(content);
					return retNews;
				}else return null;
			}else {
				System.out.println(sdf.format(System.currentTimeMillis())+"getDoc("+url+")=null");
			}
		}catch (Exception e) {
			System.out.println(sdf.format(System.currentTimeMillis())+"getZing('"+url+"') => "+e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception{
		//Connection conn = Database.getConn("localhost", "3306", "xeonline", "root", "root");
		Connection conn = Database.getConn("localhost", "3306", "car", "root", "root");
		String[] keywords = {"mercedes","chevrolet","mazda"};
		for(String keyword : keywords) {
			List<Info> is = getInfoList(keyword.toLowerCase());
			for (Info i : is) {
				String query = "INSERT INTO info (link, guid, source, public) VALUES (?, ?, ?, ?)";
				try {
					Database.execUpdate(conn, query, new String[] {	i.getLink(), i.getGuid(), i.getSource(), sdf.format(i.getPubdate()) }); 
					Crawl crawl = new Crawl();
					Method method = Crawl.class.getMethod(Const.FUNCTIONS.get(i.getSource()), String.class);
					News news = (News) method.invoke(crawl, i.getLink());
					if(news!=null) {
						query 	= "INSERT INTO content (link, source, title, summary, content, header, publicAt) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?)"; 
						Database.execUpdate(conn, query, new String[] { 
								i.getLink(), i.getSource(),news.getTitle(), 
								news.getSummary(), news.getContent(), news.getHeader(),sdf.format(i.getPubdate()) }); 
					}else {
						System.out.println(sdf.format(System.currentTimeMillis())+" - Crawler write completed to [content] table!"); 
					}
				}catch(Exception ex) {
					System.out.println(sdf.format(System.currentTimeMillis())+" - Crawler is not support "+keyword+" on source "+i.getSource()); 
				} 
			}
		}
		Database.closeConn(conn);
	}
}
