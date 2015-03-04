/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/19 20:31
 */
package com.linwoain.http;

/**
 * 网络操作
 * @author linwoain
 * @version 2014/10/19 20:31
 */
public class LHttp {

   public LHttp(){}
    
    public void sendPost(String url,LHttpParams params,LHttpHandler handler){
        
        LHttpPostTask task=new LHttpPostTask(url,params,handler);
        task.execute();
    }
    public void sendGet(String url,LHttpParams params,LHttpHandler handler){

        LHttpGetTask task=new LHttpGetTask(url,params,handler);
        task.execute();
    }
}
