package com.example.serea.mytest20;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Serea on 2016/5/10.
 */
public class Page1Activity extends Activity{
    private Button button,button2;
    //网络通信的相关定义
    private Socket socketServer = null;
    private PrintWriter pw = null;
    private BufferedReader br = null;
    private String netip = "192.168.1.103".toString();
    private int netport = 9911;
    private String nursename="nurse";
    //提醒的相关定义
    private NotificationManager nm =null;
    private PendingIntent contentIntent = null;
    private int NOTIFICATION_BASE_NUMBER=1100;
    private Notification.Builder builder = null;
    private Notification n = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1_main);
        //turn to other pages for instance, it should be connect to bluetooth
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new connectnetOnClickListener());

        button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new connectnet2OnClickListener());

        nm = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this,MainActivity.class);
        contentIntent = PendingIntent.getActivity(Page1Activity.this, 0, notificationIntent, 0);

    }
    private void notifi(){
        NotificationManager nm = (NotificationManager) Page1Activity.this.getSystemService(NOTIFICATION_SERVICE);
        Resources res = Page1Activity.this.getResources();
        builder = new Notification.Builder(Page1Activity.this);
        builder.setContentIntent(contentIntent).
                setSmallIcon(R.mipmap.ic_launcher).setTicker("allen").setWhen(System.currentTimeMillis())
                .setAutoCancel(true)//设置可以清除
                .setContentTitle("This is ContentTitle")//设置下拉列表里的标题
                .setContentText("this is ContentText");//设置上下文内容
        n = builder.getNotification();//获取一个Notification
        n.defaults =Notification.DEFAULT_SOUND;//设置为默认的声音
        nm.notify(NOTIFICATION_BASE_NUMBER, n);//显示通知 break;
        NOTIFICATION_BASE_NUMBER++;
    }
    private class connectnetOnClickListener implements View.OnClickListener{
        public void onClick(View v){
            if(socketServer==null) {
                netThread netconnect = new netThread();
                netconnect.start();
            }
            else if(!socketServer.isConnected()){
                socketServer=null;
                br=null;
                pw=null;
                netThread netconnect = new netThread();
                netconnect.start();
            }
            Intent intent = new Intent(Page1Activity.this,WelcomeActivity.class);
            startActivity(intent);
        }
    }
    private class connectnet2OnClickListener implements View.OnClickListener{
        public void onClick(View v){
            if(socketServer==null) {
                netThread netconnect = new netThread();
                netconnect.start();
            }
            else if(!socketServer.isConnected()){
                socketServer=null;
                br=null;
                pw=null;
                netThread netconnect = new netThread();
                netconnect.start();
            }
            Intent intent = new Intent(Page1Activity.this,PillowactivityActivity.class);
            startActivity(intent);
        }
    }
    private class netThread extends Thread{
        public void run(){
            try {
                socketServer = null;
                socketServer = new Socket(netip, netport);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("9");
            if (socketServer == null) {
                Toast.makeText(Page1Activity.this,"连接失败，请重试",Toast.LENGTH_SHORT).show();
            } else {
                try {
                    pw = new PrintWriter(socketServer.getOutputStream());
                    br = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(pw!=null) {
                    pw.println(nursename);
                    pw.flush();
                }
                TCPReceiveThread rev = new TCPReceiveThread();
                rev.start();
                //Toast.makeText(Page1Activity.this,"连接成功",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class TCPReceiveThread extends Thread {
        public void run() {
            if(socketServer==null||br==null)
                return;
            String str = null;
            do {
                try {
                    str = br.readLine();
                } catch (IOException e) {
                    ReConnection();
                    return;
                }
                if(str==null)
                {
                    ReConnection();
                    return;
                }
                //if(str.equals(nursename)){
                msgdeal(str);
            } while (!str.equals("exit"));
        }
        private void msgdeal(String str){
            if(str.contains("la:")) {
                String s = str.substring(str.indexOf("la:") + 3, str.length());
                //TODO Auto-generated method stub
                Data.setla((float)Double.parseDouble(s),false,0);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //slatitude[Integer.parseInt(num)]=latitude;
                }
            }
            if(str.contains("lo:")) {
                String s = str.substring(str.indexOf("lo:") + 3, str.length());
                Data.setlo((float)Double.parseDouble(s),false,0);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //slongitude[Integer.parseInt(num)]=longitude;
                }
            }
            if(str.contains("ra:")) {
                String s = str.substring(str.indexOf("ra:") + 3, str.length());
                Data.setra((float)Double.parseDouble(s),false,0);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //sradius[Integer.parseInt(num)]=radius;
                }
            }
            if(str.contains("st:")) {
                String s = str.substring(str.indexOf("st:") + 3, str.length());
                Data.setst((int)Double.parseDouble(s),false,0,false);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //sstep[Integer.parseInt(num)]=step;
                }
            }
            if(str.contains("hr:")) {
                String s = str.substring(str.indexOf("hr:") + 3, str.length());
                Data.sethr((int)Double.parseDouble(s),false,0);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //sheartrate[Integer.parseInt(num)]=heartrate;
                }
            }
            if(str.contains("ss:")) {
                String s = str.substring(str.indexOf("ss:") + 3, str.length());
                Data.setss((int)Double.parseDouble(s),false,0);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //ssleepstate[Integer.parseInt(num)]=sleepstate;
                }
            }
            if(str.contains("sr:")) {
                String s = str.substring(str.indexOf("sr:") + 3, str.length());
                Data.setsr((float)Double.parseDouble(s),false,0);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //ssleeprate[Integer.parseInt(num)]=sleeprate;
                }
            }
            if(str.contains("sleeptime:")) {
                String s = str.substring(str.indexOf("sleeptime:") + 10, str.length());
                Data.setSleeptime(s);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //ssleeprate[Integer.parseInt(num)]=sleeprate;
                }
            }
            if(str.contains("deepsleeptime:")) {
                String s = str.substring(str.indexOf("deepsleeptime:") + 14, str.length());
                Data.setDeepsleeptime(s);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //ssleeprate[Integer.parseInt(num)]=sleeprate;
                }
            }
            if(str.contains("lightsleeptime:")) {
                String s = str.substring(str.indexOf("lightsleeptime:") + 15, str.length());
                Data.setLightsleeptime(s);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //ssleeprate[Integer.parseInt(num)]=sleeprate;
                }
            }
            if(str.contains("startsleeptime:")) {
                String s = str.substring(str.indexOf("startsleeptime:") + 10, str.length());
                Data.setStartsleeptime(s);
                if(str.contains("save")){
                    String num = str.substring(0,str.indexOf("save"));
                    //ssleeprate[Integer.parseInt(num)]=sleeprate;
                }
            }
            if(str.contains("xxx")) {
                notifi();
            }
        }
    }
    private void ReConnection() {
        try
        {
            socketServer.close();
            System.out.println("3");
            socketServer=null;
        }
        catch (Exception e) {
            System.out.println("4");
        }
        System.out.println("5");
        Toast.makeText(Page1Activity.this,"服务器断开，正在重新连接",Toast.LENGTH_SHORT).show();
        netThread netconnect = new netThread();
        netconnect.start();
        System.out.println("6");
    }
}

