package com.example.twoactivitiesjava;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.twoactivitiesjava.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent returnIntent = getIntent();
                /*String message= binding.editText.getText().toString();
                returnIntent.putExtra("message",message);
                setResult(RESULT_OK,returnIntent);*/
                String name = binding.editname.getText().toString();
                String surname = binding.editsurname.getText().toString();
                String age = binding.editage.getText().toString();
                Student student = new Student(name, surname, Integer.parseInt(age));
                returnIntent.putExtra("student", student);
                setResult(RESULT_OK,returnIntent);//返回信息
                //Student student2 = returnIntent.getParcelableExtra("student");
                finish();//返回MainActivity
            } });
    }
}