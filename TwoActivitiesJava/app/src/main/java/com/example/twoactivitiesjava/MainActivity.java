package com.example.twoactivitiesjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.example.twoactivitiesjava.SecondActivity;
import com.example.twoactivitiesjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);
                //intent.putExtra("message", "This is a message from the First Activity");
                startActivityForResult(intent, 1);
            } });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                Student student = data.getParcelableExtra("student");
                binding.textView.setText("Student information are: ");
                binding.textView1.setText("Name: " + student.getName());
                binding.textView2.setText("Surname: " + student.getSurname());
                binding.textView3.setText("Age: " + Integer.toString(student.getAge()));
            }
        }
    }
}