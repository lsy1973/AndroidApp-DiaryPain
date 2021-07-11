package com.example.paindiaryhomeanddatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;


import com.example.paindiaryhomeanddatabase.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<Map<String,Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initMoodSpinner();
    }

    public void initMoodSpinner(){
        data = new ArrayList<>();
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this,getDat(),
                R.layout.item,new String[]{"image","text"}, new int[]{R.id.img,R.id.tvv});
        binding.spinnerMoodlevel.setAdapter(simpleAdapter);
    }
    private List<Map<String,Object>> getDat() {
        Map<String, Object> map = new HashMap<>();
        map.put("image", R.drawable.mood1);
        map.put("text", "level 1");
        data.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.drawable.mood2);
        map1.put("text", "level 2");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("image", R.drawable.mood3);
        map2.put("text", "level 3");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.drawable.mood4);
        map3.put("text", "level 4");
        data.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("image", R.drawable.mood5);
        map4.put("text", "level 5");
        data.add(map4);
        return data;
    }
}