package com.pay.tutoring.card;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pay.tutoring.MainActivity;
import com.pay.tutoring.R;
import com.pay.tutoring.login.SelectTeacherStudentActivity;

import java.util.regex.Pattern;

public class FragmentFinishCardProcess extends Fragment {


    public FragmentFinishCardProcess() {
    }

    public static FragmentFinishCardProcess newInstance() {
        FragmentFinishCardProcess fragment = new FragmentFinishCardProcess();
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
        View view = inflater.inflate(R.layout.fragment_finish_card_process, container, false);

        Button btn_next = view.findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                finish();
                Intent intent = new Intent(getActivity(), SelectTeacherStudentActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }

}
