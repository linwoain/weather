package com.linwoain.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.linwoain.util.LLStringTools;
import com.linwoain.util.LLogUtils;
import com.linwoain.util.ToastUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_chose_city)
public class ChoseCityActivity extends ActionBarActivity {

    private LocationClient mLocationClient;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor = "gcj02";

    @ViewById
     EditText et_city;


@AfterViews
    void init() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//设置定位模式
        option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
        int span = 3000;

        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);

        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (bdLocation == null) {
                    return;
                }
                LLogUtils.i(bdLocation.getLatitude() + "---" + bdLocation.getLongitude());
                String city = bdLocation.getCity();
                LLogUtils.i(city);

                if (!LLStringTools.isEmpty(city)) {
                    mLocationClient.stop();
                    et_city.setText(city);
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
            mLocationClient = null;
        }
        super.onDestroy();
    }


    public void location(View v) {
        LLogUtils.i("开始定位");
        mLocationClient.start();

    }

    public void ok(View v) {
        EditText et = (EditText) findViewById(R.id.et_city);
        String city = et.getText().toString().trim();

        if (LLStringTools.isEmpty(city)) {

            ToastUtil.show("当前城市名为空");

        } else {
            MainActivity_.intent(this).extra("city",city).start();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
