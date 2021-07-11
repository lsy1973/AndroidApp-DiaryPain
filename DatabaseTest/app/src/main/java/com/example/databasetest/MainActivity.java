package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.databasetest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Spinner moodspinner;
    private List<Map<String, Object>> data;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        moodspinner = binding.moodSpinner;
        data = new ArrayList<>();
        final SimpleAdapter s_adapter = new SimpleAdapter(this, getDat(),
                R.layout.item, new String[]{"image", "text"}, new int[]{R.id.img, R.id.tvv});
        moodspinner.setAdapter(s_adapter);


        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                moodspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        //为TextView控件赋值！在适配器中获取一个值赋给tv
//                        binding.tv1.setText("你选择的城市为："+s_adapter.getItem(i));
//                    }
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//                    }
//                });
                String s_mood = moodspinner.getSelectedItem().toString(); //s_mood显示了image和text信息
                //获取text字符串，也就是mood文字
                String s1 = s_mood.substring(24,31);
                binding.tv1.setText(s1);
                binding.painLocationSpinner.setEnabled(false);
                binding.painIntensityLevelSpinner.setEnabled(false);
                moodspinner.setEnabled(false);
            }
        });
        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"haha",Toast.LENGTH_LONG).show();
                binding.painLocationSpinner.setEnabled(true);
                binding.painIntensityLevelSpinner.setEnabled(true);
                moodspinner.setEnabled(true);
            }
        });
        binding.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.tv1.setText(""+minute);
                    }
                },hourOfDay,minute,true).show();
            }
        });
    }



    private List<Map<String,Object>> getDat() {
        Map<String, Object> map = new HashMap<>();
        map.put("image", R.drawable.mood6);
        map.put("text", "level 5");
        data.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.drawable.mood6);
        map1.put("text", "level 4");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("image", R.drawable.mood6);
        map2.put("text", "level 3");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.drawable.mood6);
        map3.put("text", "level 2");
        data.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("image", R.drawable.mood6);
        map4.put("text", "level 1");
        data.add(map4);
        return data;
    }
}
