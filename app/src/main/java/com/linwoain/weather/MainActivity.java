package com.linwoain.weather;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.linwoain.library.LApplication;
import com.linwoain.util.*;
import com.linwoain.weather.bean.SK;
import com.linwoain.weather.bean.Today;
import com.linwoain.weather.bean.WeaherInfo;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.net.URLEncoder;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById
    TextView sk_temp;

    @ViewById
    TextView sk_humidity;

    @ViewById
    TextView sk_wind;

    @ViewById
    TextView weather;

    @ViewById
    ProgressBar bar;

    @ViewById
    TextView tv_city;

    @ViewById
    TextView temp_room;
    @ViewById
    TextView time;

    @ViewById
    TextView id_clothe;
    @ViewById
    TextView id_comfort;
    @ViewById
    TextView one;

    String city = null;


    @AfterViews
    void init() {
        LApplication.init(this);
        Intent i = getIntent();
        String cacheCity = CacheUtil.getString("city");
        if (!LLStringTools.isEmpty(cacheCity)) {
            city = cacheCity;
        }
        if (i != null) {
            String cityIntent = i.getStringExtra("city");
            if (!LLStringTools.isEmpty(cityIntent)) {
                city = cityIntent;
            }
        }
        if (city != null) {
            CacheUtil.save("city", city);
        } else {
            city = "郑州";
        }
        tv_city.setText(city);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadWeather();
    }

    private void loadWeather() {
        /*获取天气信息的url地址*/
        String weatherUrl = "http://v.juhe.cn/weather/index?format=2&cityname=" + URLEncoder.encode(city) + "&key=0bd34cf1d6be6758c8db8f3c5316400b";
        //获取一句经典话的url地址
        String oneUrl = "http://api.lwl12.com/hitokoto/";

        LApplication.getHttpUtils().send(HttpMethod.GET, oneUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                one.setText(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                LLogUtils.e(msg);
            }
        });


        LApplication.getHttpUtils().send(HttpMethod.GET, weatherUrl, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                ToastUtil.show("网络连接错误");
                bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                bar.setVisibility(View.INVISIBLE);
                String result = arg0.result.trim();

                if (LLStringTools.isEmpty(result) || (!result.startsWith("{") && !result.startsWith("["))) {
                    ToastUtil.show("错误数据");
                    return;
                }

                WeaherInfo weaherInfo = GsonUtil.get(result, WeaherInfo.class);


                LLogUtils.i(weaherInfo);
                SK sk = weaherInfo.getResult().getSk();
                Today today = weaherInfo.getResult().getToday();
                sk_temp.setText(sk.getTemp() + "℃");
                sk_humidity.setText("湿度：" + sk.getHumidity());
                sk_wind.setText(sk.getWind_direction() + sk.getWind_strength());
                weather.setText(today.getWeather());
                temp_room.setText(today.getTemperature());
                time.setText(sk.getTime());
                id_clothe.setText("穿衣指数：" + today.getDressing_advice());
                if (LLStringTools.isEmpty(today.getConfort_index())) {
                    id_comfort.setVisibility(View.GONE);
                } else {
                    id_comfort.setText("舒适指数：" + today.getConfort_index());
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.chose_city) {

            ChoseCityActivity_.intent(this).start();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
