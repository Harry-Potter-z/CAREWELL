package com.example.serea.mytest20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Serea on 2016/5/10.
 */
public class HeartrateActivity extends Activity{
    private Button button1;
    private Button f;
    private TextView hrmsg ;
    private float a = 1;
    private Handler mHandle = null;
    private float heartrate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heartrate_main);

        button1 = (Button) findViewById(R.id.buttonback);
        hrmsg = (TextView)findViewById(R.id.text1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HeartrateActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
        mHandle = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                String strData = b.getString("info");
                hrmsg.setText(strData);
                super.handleMessage(msg);
            }
        };
    }
    private void msgshow(String message,Handler handler){
        //lock.lock();
        Bundle b = new Bundle();
        b.putString("info",message);
        Message msg = new Message();
        msg.setData(b);
        handler.sendMessage(msg);
        //lock.unlock();
    }
}
