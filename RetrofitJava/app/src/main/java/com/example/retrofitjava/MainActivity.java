package com.example.retrofitjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.retrofitjava.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "AIzaSyDPKTvSrLWi0caQ3L8N7YAzMekPEmrzuUI";
    private static final String SEARCH_ID_cx = "20a049227ceb68975";
    private String keyword;
    private ActivityMainBinding binding;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        retrofitInterface = RetrofitClient.getRetrofitService();
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = binding.editText.getText().toString();
                Call<SearchResponse> callAsync = retrofitInterface.customSearch(API_KEY, SEARCH_ID_cx, keyword);
                //makes an async request & invokes callback methods when the response returns
                callAsync.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        if (response.isSuccessful()) {
                            List<Items> list = response.body().items;
                            String result = list.get(0).getSnippet();
                            binding.tvResult.setText(result);
                        } else {
                            Log.i("Error ", "Response failed");
                        }
                    }
                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }
}