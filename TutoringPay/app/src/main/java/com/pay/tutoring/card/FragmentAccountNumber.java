package com.pay.tutoring.card;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.pay.tutoring.R;


import java.util.regex.Pattern;

public class FragmentAccountNumber extends Fragment {

    public int MAX_COUNT; // 계좌 최대 입력 갯수
    public static int BANK;
    Button button; // 동의 버튼
    TextView bankName; // 은행 이름
    TextView warningText; // 경고 문고
    EditText accountNumberEditText;
    boolean canNextButton;
    ImageView back_btn;
    int index;
    public FragmentAccountNumber() {
    }

    public static FragmentAccountNumber newInstance() {
        FragmentAccountNumber fragment = new FragmentAccountNumber();
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
        View view = inflater.inflate(R.layout.fragment_account_number, container, false);
        MAX_COUNT = 15;
        button = view.findViewById(R.id.btn_next);
        bankName = view.findViewById(R.id.bankText);
        accountNumberEditText = view.findViewById(R.id.txt_pay);
        back_btn = view.findViewById(R.id.agreement_back_btn);
        warningText = view.findViewById(R.id.warningText);
        warningText.setText("");
        BANK = 0;
        //setCardName(); // 카드 이름 세팅
        return view;
    }


    @Override
    public void onStart() {

        super.onStart();


        accountNumberEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    // 입력 글자 수 제한
                    if(charSequence.length() > MAX_COUNT){
                        accountNumberEditText.setText(accountNumberEditText.getText().toString().substring(0,MAX_COUNT));
                        accountNumberEditText.setSelection(accountNumberEditText.length());
                    }

                    Pattern ps = Pattern.compile("^[0-9]{11,15}|''+$");

                    if (!ps.matcher(charSequence).matches()) {
                        warningText.setText("계좌번호를 확인 바랍니다.");
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
                    ((CardActivity)getActivity()).replaceFragment(FragmentAccountPassword.newInstance());

//                    Intent intent = new Intent(getApplicationContext(), AccountPasswordActivity.class);
//                    intent.putExtra("card", index);
//                    startActivity(intent);
                }
            }
        });

    }

//    public void setCardName(){
//
//        Intent secondIntent = getIntent();
//
//        if(secondIntent.getIntExtra("card",0) == 0){
//            BANK = 0;
//            bankName.setText("NH농협은행");
//        } else if(secondIntent.getIntExtra("card",1) == 1){
//            BANK = 1;
//            bankName.setText("우리은행");
//        } else if(secondIntent.getIntExtra("card",2) == 2){
//            BANK = 2;
//            bankName.setText("신한은행");
//        } else if(secondIntent.getIntExtra("card",3) == 3){
//
//            bankName.setText("KB국민은행");
//        } else if(secondIntent.getIntExtra("card",4) == 4){
//
//            bankName.setText("하나은행");
//        } else if(secondIntent.getIntExtra("card",5) == 5){
//
//            bankName.setText("씨티은행");
//        } else if(secondIntent.getIntExtra("card",6) == 6){
//
//            bankName.setText("IBK기업은행");
//        } else if(secondIntent.getIntExtra("card",7) == 7){
//
//            bankName.setText("케이뱅크");
//        } else if(secondIntent.getIntExtra("card",8) == 8){
//
//            bankName.setText("카카오뱅크");
//        }
//
//    }

}
