/**
 * 文件名：BaseActivity.java      
 * 版本信息： 1.0  
 * 日期：2014年7月7日   
 * Copyright 足下 Corporation 2014    
 * 版权所有     
 */

package com.linwoain.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.linwoain.library.LApplication;
import com.linwoain.util.ScreenUtil;

/**
 * 此类描述的是： 用到的所有Activity的基类
 *
 * @author: linwoain
 * @version: 2014年7月7日 下午3:07:50
 */

public class LinActivity extends Activity {

    /**
     * 屏幕宽度
     */
    public static int WIDTH = 0;
    /**
     * 屏幕高度
     */
    public static int HEIGHT = 0;
    /**
     * 顶部标题栏中的文字
     */
    public Activity context;
    private boolean isBack = false;
    private long downTime;
    private boolean enableDoubleClickBack = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WIDTH = this.getResources().getDisplayMetrics().widthPixels; // 获取屏幕的宽度
        HEIGHT = this.getResources().getDisplayMetrics().heightPixels;// 获取屏幕的高度
        LApplication.init(this);
        context = this;

    }

    /**
     * 设置全屏，在setContentView调用前使用
     *
     * @author linwoain
     * @version 2014年9月11日 下午12:11:22
     */
    public void setFullScreen() {
        setNoTitle();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置为4.4的沉浸式状态栏,仅在4.4之后的系统中启用，不影响其他平台
     *
     * @param root 布局中的根节点
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
	public void setChenjin(View root) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (root != null) {
                root.setPadding(0, ScreenUtil.getStatusBarHeight(), 0, 0);
            }


        }
    }

    /**
     * 设置为4.4的沉浸式状态栏,仅在4.4之后的系统中启用，不影响其他平台,<br>若顶部为图片或者无背景时使用此方法
     */
    public void setChenjin() {
        setChenjin(null);
    }

    /**
     * 设置为4.4的沉浸式状态栏,仅在4.4之后的系统中启用，不影响其他平台
     *
     * @param rootId 布局中的根节点id
     */
    public void setChenjin(int rootId) {
        setChenjin(findViewById(rootId));
    }

    /**
     * 设置无标题栏
     */
    public void setNoTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * Toast显示msg
     *
     * @author: linwoain
     * @version: 2014年8月25日 下午6:21:23
     */
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast显示id为stringId
     *
     * @author linwoain
     * @version 2014年8月25日 下午6:21:23
     */
    public void showToast(int stringId) {
        Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置是否在退出前打开侧边菜单栏
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2015/1/4 15:38
     */
    protected void setShowLeftWhenPressBack(boolean b) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //处理返回按钮按下的事件
            if (enableDoubleClickBack) {
                if (!isBack) {
                    Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
                    downTime = event.getDownTime();
                    isBack = true;
                    return true;
                } else {
                    if (event.getDownTime() - downTime <= 2000) {
                        finish();
                    } else {
                        Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
                        downTime = event.getDownTime();
                        return true;

                    }
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置是否允许双击后退键退出应用，默认为否
     *
     * @param enable
     */

    protected void setEnableDoubleClickBack(boolean enable) {
        enableDoubleClickBack = enable;
    }

    /**
     * 若需要左滑的话，在此处可生效
     *
     * @author snow0358530
     */
//    public void left(View v) {
//        if (slider != null) {
//            slider.toggleLeftDrawer();
//        }
//    }
}
