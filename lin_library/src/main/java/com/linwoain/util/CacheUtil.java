/**
 * 文件名：CacheUtil.java      
 * 版本信息： 1.0  
 * 日期：2014年9月24日   
 * 版权所有     
 */

package com.linwoain.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.linwoain.bean.BaseBean;
import com.linwoain.library.LApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据缓存管理
 *
 * @author linwoain
 * @version 2014年9月24日 上午11:44:52
 */

public class CacheUtil {

    static {
        if (!isInit()) {
            initCache(LApplication.getContext());
        }
    }

    private static SharedPreferences sp;
    private static boolean isInitBoolean;
    /**
     * 数值类型的默认返回值，int、float、long<br><br/>
     * 默认为-5927
     */
    public static final int DEFAULT_INT = -5927;

    /**
     * 初始化方法,无须使用此方法，在静态代码块中已经初始化
     *
     * @param context
     * @author linwoain
     * @version 2014年9月24日 下午2:13:22
     */
    private static void initCache(Context context) {
        sp = context.getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        isInitBoolean = true;
    }

    /**
     * 获取缓存工具的状态
     *
     * @return
     * @author linwoain
     * @version 2014年9月24日 下午2:19:42
     */
    public static boolean isInit() {
        return isInitBoolean;
    }

    /**
     * 获取一个值,若未找到，则返回空字符串
     * <br> linwoain
     * <br> 2014年9月24日 上午11:44:57
     */

    public static String getString(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }

        return sp.getString(key, "");
    }

    /**
     * 保存一个键值
     *
     * @param key   要保存的键
     * @param value 要保存的值
     * @author linwoain
     * @version 2014年9月24日 上午11:51:21
     */

    public static void save(String key, Object value) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        if (value instanceof String) {
            sp.edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            sp.edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            sp.edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            sp.edit().putLong(key, (Long) value).apply();
        } else if (value instanceof BaseBean) {
            sp.edit().putString(key, ((BaseBean) value).toBase64String()).apply();
        }
        LLogUtils.d("保存了一个键值对" + key + "=" + value + "--- 类型是：" + value.getClass().getSimpleName());
    }


    /**
     * 删除list<String>中的一个值,若此list不存在，返回true
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2014/10/31 10:40
     */
    public static boolean removeItemFromListString(String key, String tobeRemoved) {
        List<String> lists = getStringList(key);
        if (lists == null) {
            //若没有这个这个list，返回true
            return true;
        }
        if (lists.contains(tobeRemoved)) {
            //若此list中有这个值
            boolean isRemoved = lists.remove(tobeRemoved);
            saveListString(key, lists);
            return isRemoved;
        } else {
            return true;
        }

    }


    /**
    *向ListString中添加新项
    *<br>作者:linwoain(linwoain@outlook.com)
    *<br>日期:2014/10/31 10:51
    * 
    */
    public static void addItemToListString(String key, String tobeAdded) {

        List<String> list = getStringList(key);
        if (list == null) {
            list = new ArrayList<String>();
        }
        list.add(tobeAdded);
        saveListString(key, list);
    }


    /**
     * 无此键值对则返回defInt， defInt可修改，默认为-9527
     *
     * @param key
     * @return
     * @author linwoain
     * @version 2014年9月25日 上午10:11:19
     */
    public static int getInt(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getInt(key, DEFAULT_INT);
    }


    /**
     * 获取保存的list，值只能是<code>List<String></code>类型
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2014/10/31 10:23
     */
    public static List<String> getStringList(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        Set<String> set = sp.getStringSet(key, null);
        List<String> lists = new ArrayList<String>();
        if (set!=null) {
            lists.addAll(set);
        }
        return lists;
    }

    /**
     * 保存一个List<String>
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2014/10/31 10:26
     */
    public static void saveListString(String key, List<String> lists) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }

        Set<String> set = new HashSet<String>();
        set.addAll(lists);
        sp.edit().putStringSet(key, set).commit();
    }

    /**
     * 返回一个Long，无此键值对则返回defInt， defInt可修改，默认为-9527
     *
     * @param key
     * @return
     * @author linwoain
     * @version 2014年9月25日 上午10:05:32
     */
    public static long getLong(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getLong(key, DEFAULT_INT);
    }

    /**
     * 返回一个float，无此键值对则返回 DEFAULT_INT
     *
     * @param key
     * @return
     * @author linwoain
     * @version 2014年9月25日 上午10:04:25
     */
    public static float getFloat(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getFloat(key, DEFAULT_INT);
    }

    /**
     * 若找不到此键值对将直接返回false
     *
     * @param key
     * @return
     * @author linwoain
     * @version 2014年9月25日 上午9:55:05
     */
    public static boolean getBoolean(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getBoolean(key, false);
    }

    /**
     * 清除所有缓存
     *
     * @author linwoain
     * @version 2014年9月24日 上午11:53:54
     */
    public static void clearAll() {
        sp.edit().clear().commit();
    }

    public static BaseBean getBaseBean(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        String string = sp.getString(key, null);
        if (LLStringTools.isEmpty(string)) {
            return null;
        }
        return BaseBean.fromBase64(string);
    }
}
