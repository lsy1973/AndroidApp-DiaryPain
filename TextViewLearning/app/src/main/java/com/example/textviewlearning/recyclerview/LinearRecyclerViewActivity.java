package com.example.textviewlearning.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.textviewlearning.R;

public class LinearRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRvrv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);
        mRvrv1 = findViewById(R.id.rv_1);
    }
}