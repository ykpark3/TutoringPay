package com.pay.tutoring.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pay.tutoring.R;
import com.pay.tutoring.calendar.FragmentCalendar;

public class FragmentPayment extends Fragment {
    public FragmentPayment() {
    }

    public static FragmentPayment newInstance() {
        FragmentPayment fragment = new FragmentPayment();
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
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        return view;
    }
}
