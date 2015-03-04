/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/23 9:38
 */
package com.linwoain.library;

import android.content.Context;
import android.view.View;

/**
 * 
 * View相关类
 * @author linwoain
 * @version 2014/10/23 9:38
 */
public class LViewHelper {
    /**
    *通过布局资源id获取相应的view对象
    *<br>作者:linwoain(linwoain@outlook.com)
    *<br>日期:2014/10/23 9:39
    * 
    */
    public static final View getView(int resId){
        return View.inflate(LApplication.getContext(),resId,null);
    }

    /**
     * 通过布局资源id获取相应的view对象
     * @param resId
     * @param context
     * @return
     */
    public static final View getView(int resId, Context context) {
        return View.inflate(context,resId,null);
    }
    
    
}
