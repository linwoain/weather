package com.linwoain.weaher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.linwoain.util.LLStringTools;
import com.linwoain.util.ToastUtil;


public class ChoseCityActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_city);
    }


    public void location(View v) {
        
    }

    public void ok(View v) {
        EditText et = (EditText) findViewById(R.id.et_city);
        String city = et.getText().toString().trim();

        if (LLStringTools.isEmpty(city)) {

            ToastUtil.show("当前城市名为空");

        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("city", city);
            startActivity(intent);
            finish();            
        } 
    }

}
