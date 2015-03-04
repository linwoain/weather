package com.linwoain.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import java.net.URLEncoder;

public class MainActivity extends ActionBarActivity {

    @ViewInject(R.id.sk_temp)
    private TextView sk_temp;

    @ViewInject(R.id.sk_humidity)
    private TextView sk_humidity;

    @ViewInject(R.id.sk_wind)
    private TextView sk_wind;

    @ViewInject(R.id.weather)
    private TextView weather;

    @ViewInject(R.id.bar)
    private ProgressBar bar;

    @ViewInject(R.id.city)
    private TextView tv_city;

    @ViewInject(R.id.temp_room)
    private TextView temp_room;
    @ViewInject(R.id.time)
    private TextView time;

    @ViewInject(R.id.id_clothe)
    private TextView id_clothe;
    @ViewInject(R.id.id_comfort)
    private TextView id_comfort;
    @ViewInject(R.id.one)
    private TextView one;

    String city = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LApplication.init(this);
        ViewUtils.inject(this);

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
        HttpUtils httpUtils = new HttpUtils();
        
        //获取天气信息的url地址
        String weatherUrl = "http://v.juhe.cn/weather/index?format=2&cityname=" + URLEncoder.encode(city) + "&key=0bd34cf1d6be6758c8db8f3c5316400b";
        //获取一句经典话的url地址
        String oneUrl = "http://api.lwl12.com/hitokoto/";
        
        httpUtils.send(HttpMethod.GET,oneUrl,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                one.setText(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                LLogUtils.e(msg);
            }
        });
        
        
        
        
        httpUtils.send(HttpMethod.GET, weatherUrl, new RequestCallBack<String>() {

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.chose_city) {

            Intent i = new Intent(this, ChoseCityActivity.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
