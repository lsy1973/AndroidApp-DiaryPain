package com.example.paindiarysecond.ui.home;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



import com.example.paindiarysecond.GetRequest_Interface;
import com.example.paindiarysecond.LoginActivity;
import com.example.paindiarysecond.R;
import com.example.paindiarysecond.Reception;

import com.example.paindiarysecond.databinding.FragmentHomeBinding;
import com.example.paindiarysecond.entity.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private static final String TAG = "Home Fragment";
    private FragmentHomeBinding binding;
    private String appid =  "93668184";
    private String cityid = "101190401";
    private String appsecret = "P4TUik0F";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        binding.tv1.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
        request();
        return root;
    }

    public void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v0.yiketianqi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<Reception> call = request.getCall(cityid,appid,appsecret);

        call.enqueue(new Callback<Reception>() {
            @Override
            public void onResponse(Call<Reception> call, Response<Reception> response) {
                String date = " ";
                String temp = " ";
                String humidity = " ";
                String pressure = " ";
                String wea_img = "";
                date = response.body().date;
                temp = response.body().tem;
                humidity = response.body().humidity;
                pressure = response.body().pressure;
                wea_img = response.body().wea_img;

                binding.tvdate.setText("Date: " + date);
                binding.tvtemp.setText("Temperature: " + temp + " ℃");
                binding.tvhumidity.setText("Humidity: " + humidity);
                binding.tvpressure.setText("Pressure: " + pressure +" hPa");

                SharedPreferences sharedPref = requireActivity().getSharedPreferences("weatherinf", Context.MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sharedPref.edit();
                spEditor.putString("temp", temp);
                spEditor.putString("humidity", humidity);
                spEditor.putString("pressure", pressure);
                spEditor.apply();

                switch (wea_img){
                    case "xue":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.xue);
                        break;
                    case "lei":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.lei);
                        break;
                    case "shachen":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.shachen);
                        break;
                    case "wu":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.wu);
                        break;
                    case "binbao":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.wu);
                        break;
                    case "yun":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.yun);
                        break;
                    case "yu":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.yu);
                        break;
                    case "yin":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.yin);
                        break;
                    case "qing":
                        binding.ivweatherimg.setBackgroundResource(R.drawable.qing);
                        break;
                }
            }

            @Override
            public void onFailure(Call<Reception> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
