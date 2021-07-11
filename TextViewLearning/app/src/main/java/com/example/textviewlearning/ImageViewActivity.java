package com.example.textviewlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageViewActivity extends AppCompatActivity {
    private ImageView mIv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        mIv4 = findViewById(R.id.iv_2);
        Glide.with(this).load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.chinazzb.com%2Fuploads%2Fallimg%2F20190822%2F1566422541776_0.jpg&refer=http%3A%2F%2Fwww.chinazzb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618478700&t=f4e0af8af696cf17c59ecf4e4c80c394").into(mIv4);
    }
}