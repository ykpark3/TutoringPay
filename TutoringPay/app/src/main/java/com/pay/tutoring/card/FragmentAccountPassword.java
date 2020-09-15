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

import com.pay.tutoring.R;

import java.util.regex.Pattern;

public class FragmentAccountPassword extends Fragment {
    public int MAX_COUNT; // 계좌 비밀번호 갯수
    EditText passwordEditText;
    TextView warningText;
    boolean canNextButton;
    ImageButton back_btn;
    Button button; // 동의 버튼
    int index;


    public FragmentAccountPassword() {
    }

    public static FragmentAccountPassword newInstance() {
        FragmentAccountPassword fragment = new FragmentAccountPassword();
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
        View view = inflater.inflate(R.layout.fragment_account_password, container, false);

        MAX_COUNT = 4;
        warningText = view.findViewById(R.id.warningPasswordText);
        passwordEditText = view.findViewById(R.id.accountPasswordEdit);
        button = view.findViewById(R.id.account_data_button);
        index = 0;
        back_btn = view.findViewById(R.id.account_password_back_btn);

        return view;
    }
    @Override
    public void onStart() {

        super.onStart();

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                // 입력 글자 수 제한

                if(charSequence.length() > MAX_COUNT){
                    passwordEditText.setText(passwordEditText.getText().toString().substring(0,MAX_COUNT));
                    passwordEditText.setSelection(passwordEditText.length());
                }

                Pattern ps = Pattern.compile("^[0-9]{0,4}+$");

                if (!ps.matcher(charSequence).matches()) {
                    warningText.setText("비밀번호를 확인 바랍니다.");
                    canNextButton = false;
                }
                else{
                    warningText.setText("");
                    canNextButton = true;
                }

                if(charSequence.length() == 0){
                    warningText.setText("");
                    canNextButton = false;
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canNextButton == true){
                    ((CardActivity)getActivity()).replaceFragment(FragmentFinishCardProcess.newInstance());

                }
            }
        });


    }
}
