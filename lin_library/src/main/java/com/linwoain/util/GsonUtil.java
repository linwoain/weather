/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/12/15 18:01
 */
package com.linwoain.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author linwoain
 * @version 2014/12/15 18:01
 */
public class GsonUtil {

    private static Gson gson;

    /**
     * 获取一个Gson的实例
     *
     * @return
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    /**
     * 将json数组转化为列表
     *
     * @param gsonStr
     * @param t
     * @return
     */

    public static <T> T get(String gsonStr, TypeToken<?> t) {
        return getGson().fromJson(gsonStr, t.getType());
    }

    /**
     * 将一个json对象转换为java对象
     *
     * @param gsonstr
     * @param classOfT
     * @return
     */
    public static <T> T get(String gsonstr, Class<T> classOfT) {

        return getGson().fromJson(gsonstr, classOfT);
    }

}
