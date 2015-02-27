package com.linwoain.weaher;

import android.os.Bundle;
import android.widget.TextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.linwoain.ui.LinActivity;
import com.linwoain.util.GsonUtil;
import com.linwoain.util.LLStringTools;
import com.linwoain.util.LLogUtils;
import com.linwoain.weaher.bean.SK;
import com.linwoain.weaher.bean.Today;
import com.linwoain.weaher.bean.WeaherInfo;

import java.net.URLEncoder;

public class MainActivity extends LinActivity {

    @ViewInject(R.id.temp)
    private TextView temp;
    @ViewInject(R.id.weather)
    private TextView weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        loadWeather();
    }

    String city = "郑州";
    String url = "http://v.juhe.cn/weather/index?format=2&cityname=" + URLEncoder.encode(city) + "&key=0bd34cf1d6be6758c8db8f3c5316400b";


    private void loadWeather() {
     
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                showToast("网络连接错误");
            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                String result = arg0.result.trim();

                if (LLStringTools.isEmpty(result) || (!result.startsWith("{") && !result.startsWith("["))) {
                    showToast("错误数据");
                    return;
                }

                WeaherInfo weaherInfo = GsonUtil.get(result, WeaherInfo.class);

                LLogUtils.i(weaherInfo);
                SK sk = weaherInfo.getResult().getSk();

                Today today = weaherInfo.getResult().getToday();
                temp.setText(sk.getTemp() + "℃");

                weather.setText(today.getWeather());
                
                
            }
        });
    }
}
