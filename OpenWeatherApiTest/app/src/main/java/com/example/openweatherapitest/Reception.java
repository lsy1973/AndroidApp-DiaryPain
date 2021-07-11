/**
  * Copyright 2021 bejson.com 
  */
package com.example.openweatherapitest;
import java.util.Date;

/**
 * Auto-generated: 2021-03-24 17:0:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Reception {

    public int cityid;
    public Date date;
    public String week;
    public String update_time;
    public String city;
    public String cityEn;
    public String country;
    public String countryEn;
    public String wea;
    public String wea_img;
    public int tem;
    public int tem1;
    public int tem2;
    public String win;
    public String win_speed;
    public String win_meter;
    public String humidity;
    public String visibility;
    public int pressure;
    public int air;
    public int air_pm25;
    public String air_level;
    public String air_tips;
    public Alarm alarm;
    public Aqi aqi;
    public static class Aqi {
        public int air;
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