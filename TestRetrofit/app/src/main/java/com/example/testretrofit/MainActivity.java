package com.example.testretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    String url = "api?version=v61&appid=93668184&appsecret=BYXa8msX&cityid=101271201";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public interface GetRequest_Interface{
        String url = "https://v0.yiketianqi.com/api?version=v61&appid=93668184&appsecret=BYXa8msX&cityid=101271201";
        @GET(url)
        Call<CurrentWeather> getCall();
    }


}