package com.pay.tutoring.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pay.tutoring.AppManager;
import com.pay.tutoring.R;
import com.pay.tutoring.calendar.FragmentCalendar;

public class FragmentStudent extends Fragment {
    public FragmentStudent() {
    }

    public static FragmentStudent newInstance() {
        FragmentStudent fragment = new FragmentStudent();
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
        View view = inflater.inflate(R.layout.fragment_student, container, false);



        return view;
    }
}
