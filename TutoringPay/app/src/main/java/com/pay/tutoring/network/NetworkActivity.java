package com.pay.tutoring.network;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.pay.tutoring.R;
import com.bumptech.glide.Glide;


public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_network);
        //setStatusBar();

        ImageView gifImageView = findViewById(R.id.wifi_gif);
        Glide.with(this).asGif()
                .load(R.drawable.network_wifi)
                .into(gifImageView);


    }


}


