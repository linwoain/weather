package com.linwoain.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.linwoain.util.LLogUtils;
import com.linwoain.util.ToastUtil;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.ReceiverAction;

@EReceiver
public class WebReceiver extends BroadcastReceiver {





    @ReceiverAction(value = Intent.ACTION_VIEW,dataSchemes = "http")
    void myAction(@ReceiverAction.Extra String valueString, Context context) {

        ToastUtil.show(valueString);
        LLogUtils.d(valueString);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
