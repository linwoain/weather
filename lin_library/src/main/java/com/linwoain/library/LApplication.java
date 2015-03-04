/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/21 14:49
 */
package com.linwoain.library;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;

/**
 * 上下文的管理类，可以从此处获取一个长期存在的 context<br/>
 * 供一些工具类使用，调用时就无须使用上下文对象<br/>
 * 如：CacheUtil
 *
 * @author linwoain
 * @version 2014/10/21 14:49
 */
public class LApplication {
	private static Context context;
	private static DbUtils dbUtils;
	private static HttpUtils httpUtils;
	private static BitmapUtils bitmapUtils;

	private LApplication() {
	}

	/**
	 * 初始化Lin_library服务<br />
	 * 需要在系统的入口类中注册或者继承LinActivity
	 *
	 * @param context
	 *            上下文
	 */
	public static void init(Context context) {
		if (LApplication.context != null) {
			return;
		}
		if (context == null) {
			throw new RuntimeException("传入的context对象为null");
		}

		LApplication.context = context.getApplicationContext();
	}

	/**
	 * 获取当前应用的上下文对象
	 *
	 * @return
	 */
	public static Context getContext() {
		if (context == null) {
			throw new RuntimeException("Lin_library未注册！！！请在程序入口处使用LApplication.init(Context context)方法初始化框架！");
		}

		return context;
	}

	/**
	 * 获取一个数据库操作工具的实例
	 * 
	 * @author linwoain  
	 * @version 2014年12月9日 上午10:10:19
	 * @return
	 */
	public static DbUtils getDbUtils() {
		if (dbUtils == null) {
			dbUtils = DbUtils.create(getContext());
		}
		return dbUtils;
	}
	
	/**
	 * 获取一个网络操作的实例
	 * 
	 * @author linwoain  
	 * @version 2014年12月9日 上午10:10:47
	 * @return
	 */
	public static HttpUtils getHttpUtils() {
		if (httpUtils==null) {
			httpUtils = new HttpUtils();
		}
		return httpUtils;
	}

	/**
	 * 获取一个位图工具的实例
	 * 
	 * @author linwoain  
	 * @version 2014年12月9日 上午10:11:00
	 * @return
	 */
	public static BitmapUtils getBitmapUtils() {
		if (bitmapUtils==null) {
			bitmapUtils=new BitmapUtils(getContext());
		}
		return bitmapUtils;
	}
	
	/**
	*<h2 color="red">完全退出当前应用</h2>
	*<br>作者:linwoain(linwoain@outlook.com)
	*<br>日期:2014/12/11 16:07
	* 
	*/
	public static void killMyself(){

		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
