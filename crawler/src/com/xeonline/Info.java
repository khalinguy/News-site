package com.xeonline;

import java.util.Date;

public class Info {
	private String 	link;		//link đầy đủ
	private String 	guid;		//mã duy nhất
	private String 	source;		//nguồn trang
	private Date	pubdate;	//ngày phát hành
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public String toString() {
		return "";
	}
}