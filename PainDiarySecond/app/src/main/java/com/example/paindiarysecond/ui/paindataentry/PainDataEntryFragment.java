package com.example.paindiarysecond.ui.paindataentry;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.paindiarysecond.AlarmReceiver;
import com.example.paindiarysecond.R;


import com.example.paindiarysecond.databinding.PainDataEntryFragmentBinding;
import com.example.paindiarysecond.entity.Customer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class PainDataEntryFragment extends Fragment {

    private CustomerViewModel customerViewModel;
    private PainDataEntryFragmentBinding binding;
    private List<Map<String, Object>> data;
    private String userEmail;
    int uid;
    String painlevel = null;
    String painlocation = null;
    String moodlevel = null;
    String stepstaken = null;
    String temp = null;
    String humidity = null;
    String pressure = null;
    String date = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PainDataEntryFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            userEmail = user.getEmail();
        } else {
            // No user is signed in
        }
        //Log.e("user Email",userEmail);

        customerViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication()).create(CustomerViewModel.class);
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonStatus();
                sendData();
                initData();

                CompletableFuture<Customer> customerCompletableFuture = customerViewModel.findByDateFuture(date);
                customerCompletableFuture.thenApply(customer -> {
                    if (customer != null) {
                        String b = customer.dateOfentry;
                        Log.d("getDate", b);
                        customer.temperature = temp;
                        customer.humidity = humidity;
                        customer.pressure = pressure;
                        customer.painLocation = painlocation;
                        customer.painIntensityLevel = painlevel;
                        customer.moodLevel = moodlevel;
                        customer.stepsTaken = stepstaken;
                        customer.email = userEmail;
                        customerViewModel.update(customer);
                        Toast.makeText(getContext(), "Update successful！", Toast.LENGTH_LONG).show();
                    } else {
                        Customer customer1 = new Customer(painlevel, painlocation, moodlevel, stepstaken, date,
                              temp, humidity, pressure, userEmail);
                        customerViewModel.insert(customer1);
                        Toast.makeText(getContext(), "Insert successful！", Toast.LENGTH_LONG).show();
                    }
                    return customer;
                });

            }
          });

        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.saveButton.setEnabled(true);
                binding.spinnerPainlevel.setEnabled(true);
                binding.spinnerPainlocation.setEnabled(true);
                binding.spinnerMoodlevel.setEnabled(true);
                binding.tvStepstaken.setEnabled(true);
                binding.tvStepsgoal.setEnabled(true);
            }
        });

        binding.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int hourOfDay = calendar.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = calendar.get(java.util.Calendar.MINUTE);
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.getTimeInMillis();
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 1);
                        calendar.add(Calendar.MINUTE,-2);
                        Toast.makeText(getContext(), "Alarm has been set successful. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PainDataEntryFragment.this.getContext(), AlarmReceiver.class);
                        PendingIntent sender = PendingIntent.getBroadcast(PainDataEntryFragment.this.getContext(), 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager) PainDataEntryFragment.this.getContext().getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                    }
                }, hourOfDay, minute, true).show();
            }
        });
        return root;
    }

    public void ButtonStatus() {
        binding.spinnerPainlevel.setEnabled(false);
        binding.spinnerPainlocation.setEnabled(false);
        binding.spinnerMoodlevel.setEnabled(false);
        binding.tvStepstaken.setEnabled(false);
        binding.tvStepsgoal.setEnabled(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        initMoodSpinner();
        binding.tvStepsgoal.setText("10000");
        getSharedData();
        getDate();
    }

    public  void getSharedData(){
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("weatherinf", Context.MODE_PRIVATE);
        temp = sharedPref.getString("temp", null);
        humidity = sharedPref.getString("humidity", null);
        pressure = sharedPref.getString("pressure", null);
        Log.e("weatherInf", temp + " " +humidity + " " + pressure);
    }

    public void getDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(System.currentTimeMillis());
        date = year + "-" + month + "-" + day; //Current Date year-month-day
    }

    public void initMoodSpinner() {
        data = new ArrayList<>();
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this.getContext(), getDat(),
                R.layout.item, new String[]{"image", "text"}, new int[]{R.id.img, R.id.tvv});
        binding.spinnerMoodlevel.setAdapter(simpleAdapter);
    }

    public void sendData(){
        SharedPreferences sharedStepsGoal = requireActivity().getSharedPreferences("StepsGoal", Context.MODE_PRIVATE);
        SharedPreferences.Editor sgEditor = sharedStepsGoal.edit();
        sgEditor.putString("StepsGoal", binding.tvStepsgoal.getText().toString());
        sgEditor.putString("StepsTaken", binding.tvStepstaken.getText().toString());
        //Log.e("goal",binding.tvStepsgoal.getText().toString());
        sgEditor.apply();
    }

    public void initData(){
        painlevel = binding.spinnerPainlevel.getSelectedItem().toString();
        painlocation = binding.spinnerPainlocation.getSelectedItem().toString();
        moodlevel = binding.spinnerMoodlevel.getSelectedItem().toString();
        int len = moodlevel.length();
        moodlevel = moodlevel.substring(24, len - 1);
        stepstaken = binding.tvStepstaken.getText().toString();
    }

    private List<Map<String, Object>> getDat() {
        Map<String, Object> map = new HashMap<>();
        map.put("image", R.drawable.mood1);
        map.put("text", "very good");
        data.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.drawable.mood2);
        map1.put("text", "good");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("image", R.drawable.mood3);
        map2.put("text", "average");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.drawable.mood4);
        map3.put("text", "low");
        data.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("image", R.drawable.mood5);
        map4.put("text", "very low");
        data.add(map4);
        return data;
    }
}