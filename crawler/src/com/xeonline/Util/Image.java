package com.xeonline.Util;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.xeonline.Const;

public class Image {
	
	@SuppressWarnings("unused")
	private static void getImage(String url) throws Exception{
		String[] tmp = url.split("\\.");
		String fileExt = "";
		if(tmp.length>0) {
			fileExt=tmp[tmp.length-1];
		}
		try(InputStream in = new URL(url).openStream()){
		    Files.copy(in, Paths.get(Const.IMAGE_FOLDER+"image_"+System.currentTimeMillis()+fileExt));
		}
	}
}
