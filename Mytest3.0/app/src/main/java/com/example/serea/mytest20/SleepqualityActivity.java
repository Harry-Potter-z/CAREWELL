package com.example.serea.mytest20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Serea on 2016/5/10.
 */
public class SleepqualityActivity extends Activity{
    private Button button1;
    private Handler sleeptimehandle = null;
    private Handler deepsleeptimehandle = null;
    private Handler lightsleeptimehandle = null;
    private Handler startsleeptimehandle = null;
    private TextView sleeptime = null;
    private TextView deepsleeptime = null;
    private TextView lightsleeptime = null;
    private TextView startsleeptime = null;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleepquality_main);

        button1=(Button)findViewById(R.id.buttonback);
        sleeptime=(TextView)findViewById(R.id.sleeptime) ;
        deepsleeptime=(TextView)findViewById(R.id.deepsleeptime) ;
        lightsleeptime=(TextView)findViewById(R.id.lightsleeptime) ;
        startsleeptime=(TextView)findViewById(R.id.startsleeptime) ;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SleepqualityActivity.this,PillowactivityActivity.class);
                startActivity(intent);
            }
        });
        handleinit();
        sleepstateshow();
    }
    private void handleinit(){
        sleeptimehandle = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                String strData = b.getString("info");
                sleeptime.setText("全天睡眠\n"+strData);
                super.handleMessage(msg);
            }
        };
        deepsleeptimehandle = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                String strData = b.getString("info");
                deepsleeptime.setText("深度睡眠\n"+strData);
                super.handleMessage(msg);
            }
        };
        lightsleeptimehandle = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                String strData = b.getString("info");
                lightsleeptime.setText("浅度睡眠\n"+strData);
                super.handleMessage(msg);
            }
        };
        startsleeptimehandle = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                String strData = b.getString("info");
                startsleeptime.setText("入睡时间\n"+strData);
                super.handleMessage(msg);
            }
        };
    }
    private void sleepstateshow(){
        msgshow(Data.getSleeptime(),sleeptimehandle);
        msgshow(Data.getDeepsleeptime(),deepsleeptimehandle);
        msgshow(Data.getLightsleeptime(),lightsleeptimehandle);
        msgshow(Data.getStartsleeptime(),startsleeptimehandle);
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

