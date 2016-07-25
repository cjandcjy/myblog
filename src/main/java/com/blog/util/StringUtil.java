package com.blog.util;

public class StringUtil {
	//判断非空
    public static boolean isNotEmpty(String str){
    	if(str!=null&&str.length()>0&&!str.equals("")){
    		return true;
    	}
    	return false;
    }
    //判断是否为空
    public static boolean isEmpty(String str){
    	if("".equals(str)||str==null){
    		return true;
    	}
    	return false;
    }
}
