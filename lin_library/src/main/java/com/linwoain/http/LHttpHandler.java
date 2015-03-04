/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/19 20:36
 */
package com.linwoain.http;

/**
 * 返回信息的接收器
 * @author linwoain
 * @version 2014/10/19 20:36
 */
public interface LHttpHandler {

    /**
     * 网络连接成功时调用此方法
     * @param result 返回的内容
     */
    public void onSuccess(String result);


    /**
     * 网络调用失败时调用此方法
     * @param why 返回原因
     */
    public void onFailure(String why);
}
