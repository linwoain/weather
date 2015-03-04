/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/19 20:39
 */
package com.linwoain.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.linwoain.util.LLogUtils;

/**
 * @author linwoain
 * @version 2014/10/19 20:39
 */
public class LHttpParams {

    private ConcurrentMap<String,String> map;
    public LHttpParams() {
        map=new ConcurrentHashMap<String, String>();
    }
    public void add(String key,String value) {
        if (key==null||value==null) {
            throw new LHttpException("key或者value不能为null");
        }
        map.put(key, value);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public String toString(){
        StringBuffer buffer=new StringBuffer();
        for (String key:map.keySet()){
            buffer.append(key).append("=").append(map.get(key)).append("&");
        }
        
        //去掉最后一个多余的 & 符号
        buffer.replace(buffer.length()-1,buffer.length(),"");
        LLogUtils.d(buffer.toString());
        try {
            return URLEncoder.encode(buffer.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return URLEncoder.encode(buffer.toString());
        }
    }
    
}
