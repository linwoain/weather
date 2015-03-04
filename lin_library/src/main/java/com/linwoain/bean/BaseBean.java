package com.linwoain.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.util.Base64;

import com.google.gson.Gson;

/**
 * 可以和字符串相互转换的对象，通过toBase64String和fromBase64方法
 *  
 * @author: linwoain   
 * @version: 2014年9月1日 下午2:22:32
 */
@SuppressWarnings("serial")
public class BaseBean implements Serializable {
	

	/**
	 * 将当前对象转换为字符串
	 * @author: linwoain  
	 * @version: 2014年9月1日 下午2:20:46
	 *
	 */
	public String toBase64String() {
		String text = "";
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			text = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * 将通过Base64编码的对象转换回来,失败返回null
	 * @author linwoain  
	 * @version 2014年9月1日 下午2:21:40
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T extends BaseBean> T  fromBase64(String base64) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(base64.getBytes(), Base64.DEFAULT));
			ObjectInputStream ois = new ObjectInputStream(bais);
			return  (T) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
    
    @Override
    public String toString(){
        return (new Gson()).toJson(this).toString();
    }
}
