package com.example.serea.mytest20;

/**
 * Created by dreamsnow on 2016/5/15.
 */
public class Data {
    //存储内容
    public static float latitude = 31;
    public static float longitude = 117;
    public static float radius = 109;
    public static int step = 0;
    public static int heartrate = 0;
    public static int sleepstate = 0;//0表示没在睡觉，1表示在睡觉
    public static float sleeprate = 0;
    public static String sleeptime = "0";
    public static String deepsleeptime = "0";
    public static String lightsleeptime = "0";
    public static String startsleeptime = "0";
    public static float[] slatitude = new float[24];//la
    public static float[] slongitude = new float[24];//lo
    public static float[] sradius = new float[24];//ra
    public static int[] sstep = new int[24];//st
    public static int[] sheartrate = new int[24];//ht
    public static int[] ssleepstate = new int[24];//ss
    public static float[] ssleeprate = new float[24];//sr
    public static float getla() {
        return latitude;
    }

    public static void setla(float la,boolean saveflag,int count) {
        Data.latitude = la;
        if(saveflag){
            Data.slatitude[count]=la;
        }
    }
    public static float getlo() {
        return longitude;
    }

    public static void setlo(float lo,boolean saveflag,int count) {
        Data.longitude = lo;
        if(saveflag){
            Data.slongitude[count]=lo;
        }
    }
    public static float getra() {
        return radius;
    }

    public static void setra(float ra,boolean saveflag,int count) {
        Data.radius = ra;
        if(saveflag){
            Data.sradius[count]=ra;
        }
    }
    public static int getst() {
        return step;
    }

    public static void setst(int st,boolean saveflag,int count,boolean clean) {
        Data.step += st;
        if(saveflag){
            Data.sstep[count]=st;
        }
    }
    public static int gethr() {
        return heartrate;
    }

    public static void sethr(int hr,boolean saveflag,int count) {
        Data.heartrate = hr;
        if(saveflag){
            Data.sheartrate[count]=hr;
        }
    }
    public static int getss() {
        return sleepstate;
    }

    public static void setss(int ss,boolean saveflag,int count) {
        Data.sleepstate = ss;
        if(saveflag){
            Data.ssleepstate[count]=ss;
        }
    }
    public static float getsr() {
        return sleeprate;
    }

    public static void setsr(float sr,boolean saveflag,int count) {
        Data.sleeprate = sr;
        if(saveflag){
            Data.ssleeprate[count]=sr;
        }
    }
    public static String getSleeptime() {
        return sleeptime;
    }

    public static void setSleeptime(String str) {
        Data.sleeptime = str;
    }
    public static String getDeepsleeptime() {
        return deepsleeptime;
    }

    public static void setDeepsleeptime(String str) {
        Data.deepsleeptime = str;
    }
    public static String getLightsleeptime() {
        return lightsleeptime;
    }

    public static void setLightsleeptime(String str) {
        Data.lightsleeptime = str;
    }
    public static String getStartsleeptime() {
        return startsleeptime;
    }

    public static void setStartsleeptime(String str) {
        Data.startsleeptime = str;
    }
}
