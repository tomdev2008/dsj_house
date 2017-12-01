package com.dsj.data.web.utils;

import com.dsj.common.utils.StringUtilsOne;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	/**
	 * 获取全拼
	 */
	public static String getPinyin(String word) {
		String res = "";
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();   
		// UPPERCASE：大写  (ZHONG)  
		// LOWERCASE：小写  (zhong)  
		
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
		// WITHOUT_TONE：无音标  (zhong)  
		// WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)  
		// WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)  
		
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		// WITH_V：用v表示ü  (nv)  
		// WITH_U_AND_COLON：用"u:"表示ü  (nu:)  
		// WITH_U_UNICODE：直接用ü (nü)  
		
		format.setVCharType(HanyuPinyinVCharType.WITH_V);  
		String[] pinyin = null ;
		try {
			if (StringUtilsOne.isNotEmpty(word)) {
				char[] words = word.toCharArray();
				for (int i = 0; i < words.length; i++) {
					pinyin = PinyinHelper.toHanyuPinyinStringArray(words[i], format);
					if (pinyin == null) {
						res += words[i];
					}else {
						res += pinyin[0];
					}
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		if (StringUtilsOne.isEmpty(res)) {
			return "";
		}
		return res.toLowerCase();
	}
	
	/**
	 * 获取第一个字母
	 */
	public static String getFirstWord(String word){
		String res = "";
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();   
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		format.setVCharType(HanyuPinyinVCharType.WITH_V);  
		String[] pinyin = null ;
		try {
			if (StringUtilsOne.isNotEmpty(word)) {
				char[] words = word.toCharArray();
				pinyin = PinyinHelper.toHanyuPinyinStringArray(words[0], format);
				if (pinyin == null) {
					res += words[0];
				}else {
					res += pinyin[0];
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		if (StringUtilsOne.isEmpty(res)) {
			return "";
		}
		return res.substring(0, 1).toUpperCase();
	}
	
	/**
	 * 获取拼音简写 
	 */
	public static String getFirstWords(String word){
		String res = "";
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();   
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		format.setVCharType(HanyuPinyinVCharType.WITH_V);  
		String[] pinyin = null ;
		try {
			if (StringUtilsOne.isNotEmpty(word)) {
				char[] words = word.toCharArray();
				for (int i = 0; i < words.length; i++) {
					pinyin = PinyinHelper.toHanyuPinyinStringArray(words[i], format);
					if (pinyin == null) {
						res += words[i];
					}else {
						res += pinyin[0].substring(0, 1);
					}
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		if (StringUtilsOne.isEmpty(res)) {
			return "";
		}
		return res.toLowerCase();
	}

}
