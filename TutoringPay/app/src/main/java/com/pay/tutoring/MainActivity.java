package com.pay.tutoring;

import android.content.ClipData;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pay.tutoring.calendar.FragmentCalendar;
import com.pay.tutoring.notification.FragmentNotifications;
import com.pay.tutoring.payment.FragmentPayment;
import com.pay.tutoring.setting.FragmentSetting;
import com.pay.tutoring.student.FragmentStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private FragmentCalendar fragmentCalendar =  FragmentCalendar.newInstance();
    private FragmentStudent fragmentStudent =  FragmentStudent.newInstance();
    private FragmentNotifications fragmentNotifications = FragmentNotifications.newInstance();
    private FragmentPayment fragmentPayment = FragmentPayment.newInstance();
    private FragmentSetting fragmentSetting = FragmentSetting.newInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        //getHashKey();//함수 주석풀어서 해시키 구한 후 모은한테 알려주셈

        //초기 프래그먼트 설정
        fragmentCalendar = FragmentCalendar.newInstance();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragmentCalendar).commit();

        //bottom navigation view 설정
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }


    //fragment 전환하는 메소드
    public void replaceFragment(Fragment fragment){
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();
    }

    // 하단 메뉴 선택 리스너
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {

                case R.id.calendar:
                    replaceFragment(fragmentCalendar.newInstance());
                    break;

                case R.id.student:
                    replaceFragment(fragmentStudent.newInstance());
                    break;

                case R.id.payment:
                    replaceFragment(FragmentPayment.newInstance());
                    break;

                case R.id.notification:
                    replaceFragment(fragmentNotifications.newInstance());
                    break;
                case R.id.setting:
                    replaceFragment(fragmentSetting.newInstance());
                    break;
            }
            return true;
        }
    }
    /**
     * 상태바 색을 바꾸는 함수
     */
    private void setStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#add8e6"));

    }
    /**
     * 카카오 로그인을 위한 해시키 구하기
     */
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)

            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch ( NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}