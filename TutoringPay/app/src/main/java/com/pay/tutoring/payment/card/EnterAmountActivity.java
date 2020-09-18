package com.pay.tutoring.payment.card;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pay.tutoring.AppManager;
import com.pay.tutoring.R;
import com.pay.tutoring.network.RetrofitClient;
import com.pay.tutoring.network.ServiceApi;
import com.pay.tutoring.payment.FragmentPayment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//2
public class EnterAmountActivity extends AppCompatActivity {
    private ServiceApi service;
    private TextView txt_pay;
    private String pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_amount);

         txt_pay = (TextView)findViewById(R.id.txt_pay);



        // BtnOnClickListener의 객체 생성.
        EnterAmountActivity.BtnOnClickListener onClickListener = new EnterAmountActivity.BtnOnClickListener();

        //각 Button의 이벤트 리스터로 onClickListener 지정
        Button btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(onClickListener);
    }
    class BtnOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            // 일단은 intent로 넘기는 걸로 하고, 서버 되면 바꿔놓기
            switch (view.getId())
            {
                case R.id.btn_next:
                    // 현재시간을 msec 으로 구한다.
                    long now = System.currentTimeMillis();
                    // 현재시간을 date 변수에 저장한다.
                    Date date = new Date(now);
                    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                    SimpleDateFormat sdfNow = new SimpleDateFormat("HHmmss");
                    SimpleDateFormat sdfDay = new SimpleDateFormat("YYYYMMdd");

                    String formatDay = sdfDay.format(date);
                    // nowDate 변수에 값을 저장한다.
                    String formatDate = sdfNow.format(date);

                    AccountVO account = AppManager.getInstance().getCurrentTransfer();
                    Editable edtStr = (Editable)txt_pay.getText();
                    account.setPay((Integer.parseInt(String.valueOf(edtStr))));
                    Log.i("모은 pay =",String.valueOf(edtStr));
                    transferToAccount("HKT00015","q",formatDay,formatDate,"102","00003",50000);



                    break;
            }
        }
    }
    protected void transferToAccount(String OGN_CD,String CUST_ID, String TRAN_DT,String TRAN_TM, String TRAN_DIV ,String TRAN_SEQ,int TRAN_AMT) {
        service = RetrofitClient.getCooConClient().create(ServiceApi.class);
        Call<ResponseBody> data = service.get102Data(OGN_CD,CUST_ID,TRAN_DT,TRAN_TM,TRAN_DIV,TRAN_SEQ,TRAN_AMT);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.i("모은", response.body().string());
                    try{
                        JSONObject jsonObject = new JSONObject(result);
                        String OGN_CD;
                        String CUST_ID;
                        String TRAN_DT;
                        String TRAN_TM;
                        String TRAN_DIV;
                        String TRAN_SEQ;//거래일련번호
                        String TRAN_AMT;//결제금액
                        String REPY_CD;//응답코드
                        REPY_CD = jsonObject.getString("REPY_CD");
                        Log.i("모은 transferToAccount", REPY_CD);

                    }
                    catch(JSONException e){
                        e.printStackTrace();

                    }finally{
                        finish();
                        Intent intent = new Intent(getApplicationContext(), FinishTransfer.class);
                        startActivity(intent);

                        //잔액 정보 조회
                        //서버에 결제 내역 저장
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}