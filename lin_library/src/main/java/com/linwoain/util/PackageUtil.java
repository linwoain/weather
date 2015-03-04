/**
 * Created by linwoain(linwoain@outlook.com)
 * Time  2014/10/13 16:20
 * 知我者谓我心忧，不知我者谓我何求！ 
 */
package com.linwoain.util;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import com.linwoain.library.LApplication;

/**
 * @author linwoain
 * @version 2014/10/13 16:20
 */
public class PackageUtil {


    /**
     * 获取清单文件中meta_data标签下自定义的键值对<br>
     * 在Application节点下
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2014/11/3 14:44
     */
    public static String getMetaData(String key) {
        ApplicationInfo info1 = null;
        try {
            info1 = LApplication.getContext().getPackageManager().getApplicationInfo(LApplication.getContext().getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info1.metaData.getString(key);


    }

    /**
     * 获取版本名
     *
     * @return 版本名
     */
    public static String getVersionName() {
        String version = getPackageInfo().versionName;
        return version;
    }

    /**
     * 获取版本号
     *
     * @return 版本号
     */
    public static int getVersionCode() {
        int version = getPackageInfo().versionCode;
        return version;
    }

    /**
     * 获取包信息
     *
     * @return 包信息
     */
    public static PackageInfo getPackageInfo() {
        Context context = LApplication.getContext();
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo;
    }

    /**
     * 此方法描述的是：根据文件名获取保存到本地的地址
     *
     * @param path
     * @return
     * @author linwoain
     * @version 2014年7月11日 下午2:43:34
     */

    public static String getpath(String path) {
        Context context = LApplication.getContext();
        boolean is = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (is && context.getExternalCacheDir().canWrite()) {
            return context.getExternalCacheDir().getPath() + "/" + path;
        } else {
            return context.getCacheDir().getPath() + path;

        }

    }


    /**
     * 调用系统应用安装APK程序
     *
     * @param path
     */
    public static void installApk(String path) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        File file = new File(path);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LApplication.getContext().startActivity(intent);
    }

    /**
     * 根据应用包名打开应用
     *
     * @param packageName 要打开的应用
     */
    public static void startApp(String packageName) {
        if (LLStringTools.isEmpty(packageName)) {
            throw new RuntimeException("传入的包名不能为空");
        }
        PackageManager manager = LApplication.getContext().getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(packageName);
        LApplication.getContext().startActivity(intent);
    }

    /**
     * 调用系统自带卸载API
     * <br>作者:linwoain(linwoain@outlook.com)
     * <br>日期:2014/12/10 8:56
     */
    public static void uninstall(String packageName) {

        if (LLStringTools.isEmpty(packageName)) {
            ToastUtil.show("卸载失败！传入的包名为空");
            return;
        }
//12-11 15:19:59.274: I/ActivityManager(598): 
// START {act=android.intent.action.DELETE dat=package cmp=com.android.packageinstaller/.UninstallerActivity u=0} from pid 31719

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LApplication.getContext().startActivity(intent);
    }
}
