package com.pay.tutoring.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pay.tutoring.AppManager;
import com.pay.tutoring.MainActivity;
import com.pay.tutoring.R;
import com.pay.tutoring.card.CardActivity;
import com.pay.tutoring.card.FragmentCard;
import com.pay.tutoring.data.LoginData;
import com.pay.tutoring.data.LoginResponse;
import com.pay.tutoring.network.RetrofitClient;
import com.pay.tutoring.network.ServiceApi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 서버에 로그인 정보 보내고
 * 돈을 받을 계좌 연결
 */
public class AgreePersonalInfortmation extends AppCompatActivity {
    private ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree_personal_infortmation);

        service = RetrofitClient.getClient().create(ServiceApi .class);


        // BtnOnClickListener의 객체 생성.
        AgreePersonalInfortmation.BtnOnClickListener onClickListener = new AgreePersonalInfortmation.BtnOnClickListener();

        //각 Button의 이벤트 리스터로 onClickListener 지정
        Button btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(onClickListener);

    }

    class BtnOnClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            // 일단은 intent로 넘기는 걸로 하고, 서버 되면 바꿔놓기


            switch (view.getId()) {
                case R.id.btn_next:
                    Log.i("모은", "next 버튼 누름");

                    startLogin(new LoginData(111,"111","111"));

                    finish();
                    Intent intent = new Intent(AgreePersonalInfortmation.this, CardActivity.class);
//                    intent.putExtra("setting", "login");
                    startActivity(intent);

                    break;
            }

        }
    }

    /****** 서버에 데이터 보내기 *****/
    protected void startLogin(LoginData data) {

        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Log.i("모은",result.toString());
                Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();

                Log.i("모은", "startLogin 들어옴");


            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("모은 ", " 로그인 에러 발생"+t.getMessage());

            }
        });
    }
}