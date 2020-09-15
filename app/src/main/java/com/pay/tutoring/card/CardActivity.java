package com.pay.tutoring.card;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pay.tutoring.R;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private FragmentCard fragmentCard= FragmentCard.newInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        // 초기 프래그먼트 설정
        fragmentCard = fragmentCard.newInstance();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragmentCard).commit();


    }

    //fragment 전환하는 메소드
    public void replaceFragment(Fragment fragment){
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();
    }
    public void replaceAndGetBundleFragment(Fragment fragment, Fragment bundleFragment)
    {

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();  // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.

    }

}
