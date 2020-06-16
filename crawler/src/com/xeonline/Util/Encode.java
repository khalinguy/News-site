package com.xeonline.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

public class Encode {
	
	public static String MD5(String input) { 
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	public static String UUID() throws Exception{
		return UUID.randomUUID().toString();
	}
	
	public static boolean checkUUID(String input){
		try {
			return UUID.fromString(input)!=null;
		}catch (Exception e) {
			return false;
		}
	}
}
