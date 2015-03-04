package com.linwoain.util;

import java.lang.reflect.Field;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.linwoain.library.LApplication;

/**
 * 获取屏幕相关参数
 */
public class ScreenUtil {

	private static int sbar = 0;

	/**
	 * 获取屏幕宽度，px
	 */
	public static int getWidth() {
		return LApplication.getContext().getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕宽度，dx
	 */
	public static int getDPWidth() {
		return px2dp(LApplication.getContext().getResources()
				.getDisplayMetrics().widthPixels);
	}

	/**
	 * 获取屏幕高度，px
	 */
	public static int getHeight() {
		return LApplication.getContext().getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 获取屏幕高度，dp
	 */
	public static int getDPHeight() {
		return px2dp(LApplication.getContext().getResources()
				.getDisplayMetrics().heightPixels);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dp2px(float dpValue) {
		final float scale = LApplication.getContext().getResources()
				.getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dp(float pxValue) {
		final float scale = LApplication.getContext().getResources()
				.getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 设置为4.4的沉浸式状态栏
	 *
	 * @param activity
	 *            当前Activity对象
	 * @param root
	 *            当前布局文件中的根view，此view背景色应该与状态栏背景色相同
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static void setchenjin(Activity activity, View root) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			activity.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			// activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			if (root != null) {
				root.setPadding(0, ScreenUtil.getStatusBarHeight(), 0, 0);
			}

		}
	}

	/**
	 * 获取状态栏高度
	 *
	 * @return
	 */
	public static int getStatusBarHeight() {
		if (sbar > 0) {
			return sbar;
		}
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			sbar = LApplication.getContext().getResources()
					.getDimensionPixelSize(x);
		} catch (Exception e1) {
			LLogUtils.e("get status bar height fail");
			e1.printStackTrace();
		}
		return sbar;
	}

	public static void setchenjin(Activity act) {
		setchenjin(act, null);
	}

	public static void setChenjinOrFullScreen(Activity context, View v) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setchenjin(context, v);
		} else {
			context.requestWindowFeature(Window.FEATURE_NO_TITLE);
			context.getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}
}