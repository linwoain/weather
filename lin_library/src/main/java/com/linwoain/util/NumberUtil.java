/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/29 11:43
 */
package com.linwoain.util;

/**
 * 操作数字的工具类
 *
 * @author linwoain
 * @version 2014/10/29 11:43
 */
public class NumberUtil {

    private static String string;

    private NumberUtil() {
    }


    /**
     * 从字符中提取数字，其它情况则返回0
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2014/10/29 11:49
     */
    public static float fromStr(String str) {

        StringBuilder builder = new StringBuilder();
        if (LLStringTools.isEmpty(str)) {
            return 0;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                builder.append(str.charAt(i));
            }
            if (str.charAt(i) == ('.')) {
                builder.append(".");
            }
        }
        if (builder.length() > 0) {
            return Float.parseFloat(builder.toString());
        }

        return 0;

    }


    public static String toSaveFormat(float number, int saved) {

        String numberStr = String.valueOf(number);
        //获取小数点后面的位数
        int length = numberStr.substring(numberStr.indexOf(".") + 1, numberStr.length()).length();
        if (length <= saved) {
            StringBuilder builder = new StringBuilder(numberStr);
            for (int i = 0; i < saved; i++) {
                builder.append(0);
            }
            string = builder.toString();
            return string;
        }

        if (length > 2) {
            return numberStr.substring(0, numberStr.indexOf(".") + 2);
        }

        return null;
    }

}
