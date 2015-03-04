/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/16 18:24
 */
package com.linwoain.util;

import java.io.File;

import android.app.ProgressDialog;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.linwoain.ui.LinActivity;

/**
 * 更新APK文件
 *
 * @author linwoain
 * @version 2014/10/16 18:24
 */
public class UpdateService {

    /**
     * 下载并安装更新
     *
     * @param context
     * @param url
     * @return 下载完成的路径
     */
    public static void updateApp(final LinActivity context, String url) {

        final ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setTitle("升级中...");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载");
        pd.setMax(100);
        final String path = PackageUtil.getpath(System.currentTimeMillis() + ".apk");
        HttpUtils utils = new HttpUtils();
        utils.download(url, path, true, true, new RequestCallBack<File>() {

            @Override
            public void onSuccess(ResponseInfo<File> arg0) {
                PackageUtil.installApk(arg0.result.getPath());

            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                pd.dismiss();

                context.showToast("下载失败，请稍后尝试！！");
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                LogUtils.i(Thread.currentThread().getName());

                LogUtils.i("current：" + current + "--total:" + total);
                if (!pd.isShowing()) {
                    pd.show();
                }
                int value = (int) (100 * current / total);
                pd.setProgress(value);
                LLogUtils.i("下载进度：" + value);
            }

        });
    }


  
}
