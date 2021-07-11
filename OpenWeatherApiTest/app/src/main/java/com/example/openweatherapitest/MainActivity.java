package com.example.openweatherapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView textView;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv1);
        request();

        //tv1.setText(getText());
    }
    private void request(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v0.yiketianqi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<Reception> call = request.getCall();
        call.enqueue(new Callback<Reception>() {
            @Override
            public void onResponse(Call<Reception> call, Response<Reception> response) {
                String s = null;
                s = response.body().city;
                Log.e(TAG,s);
                textView.setText(s);
            }
            @Override
            public void onFailure(Call<Reception> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}