package com.dsj.common.utils.code;

import java.util.Date;
import java.util.Random;

import com.dsj.common.utils.DateUtils;




public class CodeUtils {

	private static String getFixLenthString(int strLength) {  
	      
	    Random rm = new Random();  
	      
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
	  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 2).replace(".", "");  
	}  
	   public static String  getYMDand7NumCode(){
		   return DateUtils.date2String(new Date(),"yyyyMMdd")+getFixLenthString(7);
	   }
	   
	   public static String  getYMDHMS4NumCode(){
		   return DateUtils.date2String(new Date(),"yyyyMMddHHmmss")+getFixLenthString(4);
	   }
	   
	   public static String  getErshouCode(){
		   return getYMDHMS4NumCode();
	   }
	   
	  public static int getSixCode(){
		  return (int)((Math.random()*9+1)*100000);
	  }
	    
	 public static void main(String[] args) {
		while(true){
		 System.out.println(getSixCode());
		}
	}
}
