package com.linwoain.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 
 * 字符串相关操作
 * 
 * @author linwoain
 * @version 2014年8月25日 下午6:35:07
 */
public class LLStringTools {

	/**
	 * 判断给定字符串是否为空
	 * 
	 * @author linwoain
	 * @version 2014年8月25日 下午6:35:29
	 * 
	 */
	public static boolean isEmpty(String str) {
		if (str == null ||str.length()==0) {
			return true;
		}
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}

		return true;
	}

	/**
	 * 将一个InputStream流转换成字符串
	 * 
	 * @author linwoain
	 * @version 2014年8月25日 下午6:46:22
	 * 
	 */
	public static String stream2String(InputStream in) {
		InputStreamReader sr = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(sr);
		StringBuffer buffer = new StringBuffer();
		try {

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
				if (sr != null) {
					sr.close();
					sr = null;
				}

				if (in != null) {
					in.close();
					in = null;
				}

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return buffer.toString();

	}

	/**
	 * 获取当前时间,以yyyy-MM-dd:hh:mm:ss格式
	 * @author linwoain  
	 * @version 2014年8月26日 上午9:33:07
	 *
	 */
	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss",Locale.getDefault());
		return format.format(System.currentTimeMillis());
	}
	/**
	 * Md5加密
	 * @author linwoain  
	 * @version 2014年8月26日 上午9:37:00
	 *
	 */
	public String encode2Md5(String str) {

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[]  bytes = digest.digest(str.getBytes());
			StringBuffer sb = new  StringBuffer();
			for(int i = 0;i<bytes.length;i++){
				String s = Integer.toHexString(0xff&bytes[i]);
				
				if(s.length()==1){
					sb.append("0"+s);
				}else{
					sb.append(s);
				}
			}
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("buhuifasheng");
		}
	
		
	}
}
