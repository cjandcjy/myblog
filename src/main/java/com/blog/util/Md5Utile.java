package com.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.corba.se.impl.naming.cosnaming.InternalBindingKey;

public class Md5Utile {
   //加密算法
	public static String MD5(String str){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			//获得MD5加密工具
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			//根据摘要获得密文
			mdInst.update(str.getBytes());
			byte[] md = mdInst.digest();
			//将密文转换为16进制数字的字符串
			int j = md.length;
			int k = 0;
			char[] strs = new char[j*2];
			for(int i=0;i<j;i++){
			   byte byte0 = md[i];
			   strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
			   strs[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(strs);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
