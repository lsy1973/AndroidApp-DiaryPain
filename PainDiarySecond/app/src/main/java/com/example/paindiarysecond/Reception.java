package com.example.paindiarysecond;

/**
this is a class storing information from weather API
 */
// My Weather Api is https://v0.yiketianqi.com/api?version=v61&appid=93668184&appsecret=BYXa8msX&cityid=101010100
public class Reception {
    public int cityid;
    public String date;
    public String week;
    public String update_time;
    public String city;
    public String cityEn;
    public String country;
    public String countryEn;
    public String wea;
    public String wea_img;
    public String tem;
    public String tem1;
    public String tem2;
    public String win;
    public String win_speed;
    public String win_meter;
    public String humidity;
    public String visibility;
    public String pressure;
    public String air;
    public String air_pm25;
    public String air_level;
    public String air_tips;
    public Alarm alarm;
    public Aqi aqi;
    public static class Aqi {
        public String air;
        public String air_level;
        public String air_tips;
        public int pm25;
        public String pm25_desc;
        public int pm10;
        public String pm10_desc;
        public int o3;
        public String o3_desc;
        public int no2;
        public String no2_desc;
        public int so2;
        public String so2_desc;
        public int co;
        public String co_desc;
        public String kouzhao;
        public String waichu;
        public String yundong;
        public String kaichuang;
        public String jinghuaqi;
        public int cityid;
        public String city;
        public String cityEn;
        public String country;
        public String countryEn;
    }
    public static class Alarm {
        public String alarm_type;
        public String alarm_level;
        public String alarm_content;
    }
}