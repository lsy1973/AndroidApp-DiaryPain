package com.example.paindiary.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.paindiary.GetRequest_Interface;
import com.example.paindiary.Reception;
import com.example.paindiary.databinding.HomeFragmentBinding;
import com.example.paindiary.viewmodel.SharedViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private SharedViewModel model;
    private HomeFragmentBinding binding;
    private static final String TAG = "MainActivity";
    public HomeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        request();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void request(){
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
                Log.e(TAG, s);
                binding.tvWeather.setText(s);
            }
            @Override
            public void onFailure(Call<Reception> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
