package com.example.testretrofit;
public class CurrentWeather {
    /**
     * cityid : 101271201
     * date : 2020-07-15
     * week : 星期三
     * update_time : 10:15
     * city : 内江
     * cityEn : neijiang
     * country : 中国
     * countryEn : China
     * wea : 阴
     * wea_img : yin
     * tem : 25
     * tem1 : 29
     * tem2 : 23
     * win : 西风
     * win_speed : 2级
     * win_meter : 小于12km/h
     * humidity : 97%
     * visibility : 24.22km
     * pressure : 961
     * air : 15
     * air_pm25 : 8
     * air_level : 优
     * air_tips : 空气很好，可以外出活动，呼吸新鲜空气，拥抱大自然！
     * alarm : {"alarm_type":"暴雨","alarm_level":"橙色","alarm_content":"内江市气象台7月15日5时23分发布第16号暴雨橙色预警信号:预计我市市中区部分地方3小时降雨量将达50毫米以上，并伴有雷电和阵性大风，请加强防范强降水、雷电和大风等带来的危害。（预警信息来源：国家预警信息发布中心）"}
     * aqi : {"air":"15","air_level":"优","air_tips":"空气很好，可以外出活动，呼吸新鲜空气，拥抱大自然！","pm25":"8","pm25_desc":"优","pm10":"10","pm10_desc":"优","o3":"47","o3_desc":"优","no2":"12","no2_desc":"优","so2":"8","so2_desc":"优","co":"1","co_desc":"优","kouzhao":"无需戴口罩","waichu":"适宜外出","yundong":"适宜运动","kaichuang":"适宜开窗","jinghuaqi":"关闭净化器","cityid":"101271201","city":"内江","cityEn":"neijiang","country":"中国","countryEn":"China"}
     */

    private String cityid;
    private String date;
    private String week;
    private String updateTime;
    private String city;
    private String cityEn;
    private String country;
    private String countryEn;
    private String wea;
    private String weaImg;
    private String tem;
    private String tem1;
    private String tem2;
    private String win;
    private String winSpeed;
    private String winMeter;
    private String humidity;
    private String visibility;
    private String pressure;
    private String air;
    private String airPm25;
    private String airLevel;
    private String airTips;
    private AlarmDTO alarm;
    private AqiDTO aqi;

    public static class AlarmDTO {
        /**
         * alarm_type : 暴雨
         * alarm_level : 橙色
         * alarm_content : 内江市气象台7月15日5时23分发布第16号暴雨橙色预警信号:预计我市市中区部分地方3小时降雨量将达50毫米以上，并伴有雷电和阵性大风，请加强防范强降水、雷电和大风等带来的危害。（预警信息来源：国家预警信息发布中心）
         */

        private String alarmType;
        private String alarmLevel;
        private String alarmContent;
    }

    public static class AqiDTO {
        /**
         * air : 15
         * air_level : 优
         * air_tips : 空气很好，可以外出活动，呼吸新鲜空气，拥抱大自然！
         * pm25 : 8
         * pm25_desc : 优
         * pm10 : 10
         * pm10_desc : 优
         * o3 : 47
         * o3_desc : 优
         * no2 : 12
         * no2_desc : 优
         * so2 : 8
         * so2_desc : 优
         * co : 1
         * co_desc : 优
         * kouzhao : 无需戴口罩
         * waichu : 适宜外出
         * yundong : 适宜运动
         * kaichuang : 适宜开窗
         * jinghuaqi : 关闭净化器
         * cityid : 101271201
         * city : 内江
         * cityEn : neijiang
         * country : 中国
         * countryEn : China
         */

        private String air;
        private String airLevel;
        private String airTips;
        private String pm25;
        private String pm25Desc;
        private String pm10;
        private String pm10Desc;
        private String o3;
        private String o3Desc;
        private String no2;
        private String no2Desc;
        private String so2;
        private String so2Desc;
        private String co;
        private String coDesc;
        private String kouzhao;
        private String waichu;
        private String yundong;
        private String kaichuang;
        private String jinghuaqi;
        private String cityid;
        private String city;
        private String cityEn;
        private String country;
        private String countryEn;
    }
}
