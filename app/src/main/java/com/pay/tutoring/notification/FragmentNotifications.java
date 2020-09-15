package com.pay.tutoring.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pay.tutoring.R;
import com.pay.tutoring.payment.FragmentPayment;

public class FragmentNotifications extends Fragment {
    public FragmentNotifications() {
    }

    public static FragmentNotifications newInstance() {
        FragmentNotifications fragment = new FragmentNotifications();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        return view;
    }
}
