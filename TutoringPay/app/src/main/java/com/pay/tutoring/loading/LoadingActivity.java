package com.pay.tutoring.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.pay.tutoring.R;
import com.pay.tutoring.login.AgreePersonalInfortmation;
import com.pay.tutoring.login.LoginActivity;
import com.pay.tutoring.network.NetworkActivity;
import com.pay.tutoring.network.NetworkStatus;

public class LoadingActivity extends AppCompatActivity {

    NetworkStatus networkStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        int networkSatusNum = networkStatus.getConnectivityStatus(getApplicationContext());
        if (networkSatusNum == networkStatus.TYPE_NOT_CONNECTED)
            goToNextActivity(new NetworkActivity());
        else
            goToNextActivity(new LoginActivity());

    }

    private void goToNextActivity(Activity activity) {

        Log.i("모은", "goToNextActivity");
        finish();
        Intent intent = new Intent(getApplicationContext(), activity.getClass());
        startActivity(intent);
    }
}