/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/30 15:25
 */
package com.linwoain.util;

import java.io.File;

/**
 * 文件操作
 *
 * @author linwoain
 * @version 2014/10/30 15:25
 */
public class FileUtil {

    /**
     * 修改后缀为指定的
     *
     * @param path   要更改的文件路径
     * @param suffix 指定的文件后缀
     * @return
     */
    public static boolean changeSuffixTo(String path, String suffix) {

        File file = new File(path);   //指定文件名及路径
        String filename = file.getAbsolutePath();
        if (filename.indexOf(".") >= 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        return file.renameTo(new File(filename + "." + suffix));   //改名   
    }

    /**
     * 删除指定文件
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2014/10/31 11:16
     */
    public static boolean deleteByPath(String path) {

        File file = new File(path);
        return file.delete();
    }
}

