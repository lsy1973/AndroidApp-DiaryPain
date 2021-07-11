package com.example.paindiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.paindiary.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.appBar.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_fragment,R.id.pain_diary_entry_fragment,R.id.diary_record_fragment,R.id.reports_fragment,R.id.maps_fragment)
                .setOpenableLayout(binding.drawerLayout).build();
        FragmentManager fragmentManager= getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)
                fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.appBar.toolbar,navController, mAppBarConfiguration);




        //request();
    }
//    private void request(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://v0.yiketianqi.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
//        Call<Reception> call = request.getCall();
//        call.enqueue(new Callback<Reception>() {
//            @Override
//            public void onResponse(Call<Reception> call, Response<Reception> response) {
//                String s = null;
//                s = response.body().city;
//                //Log.e(TAG,s);
//                binding.eh1.setText(s);
//            }
//            @Override
//            public void onFailure(Call<Reception> call, Throwable t) {
//                Log.e(TAG, t.toString());
//            }
//        });
//    }
}