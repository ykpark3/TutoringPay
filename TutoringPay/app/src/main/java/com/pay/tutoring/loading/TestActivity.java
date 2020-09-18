package com.pay.tutoring.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.pay.tutoring.AppManager;
import com.pay.tutoring.R;
import com.pay.tutoring.network.RetrofitClient;
import com.pay.tutoring.network.ServiceApi;
import com.pay.tutoring.payment.card.AccountVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        service = RetrofitClient.getCooConClient().create(ServiceApi.class);

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
        transferToAccount("HKT00015","q",formatDay,formatDate,"102","11111",50000);

//        Log.i("모은",formatDay);
//        checkBalance("HKT00015", "q", formatDay, formatDate, "101");
    }


    protected void transferToAccount(String OGN_CD, String CUST_ID, String TRAN_DT, String
            TRAN_TM, String TRAN_DIV, String TRAN_SEQ, int TRAN_AMT) {

        Call<ResponseBody> data = service.get102Data(OGN_CD, CUST_ID, TRAN_DT, TRAN_TM, TRAN_DIV, TRAN_SEQ, TRAN_AMT);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.i("모은", response.body().string());
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String OGN_CD;
                        String CUST_ID;
                        String TRAN_DT;
                        String TRAN_TM;
                        String TRAN_DIV;
                        String TRAN_SEQ;//거래일련번호
                        String TRAN_AMT;//결제금액d
                        String REPY_CD;//응답코드
                        REPY_CD = jsonObject.getString("REPY_CD");
                        Log.i("모은", REPY_CD);

                    } catch (JSONException e) {
                        e.printStackTrace();

                        //잔액조회
                        // 현재시간을 msec 으로 구한다.
                        long now = System.currentTimeMillis();
                        // 현재시간을 date 변수에 저장한다.
                        Date date = new Date(now);
                        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                        SimpleDateFormat sdfNow = new SimpleDateFormat("HHmmss");
                        SimpleDateFormat sdfDay = new SimpleDateFormat("YYYYMMDD");

                        String formatDay = sdfDay.format(date);
                        // nowDate 변수에 값을 저장한다.
                        String formatDate = sdfNow.format(date);
                        Log.i("모은", formatDay);
                        //checkBalance("HKT00015", "q", "20200917", "153951", "101");

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

    /**
     * 잔액조회
     *
     * @param OGN_CD   //기관코드
     * @param CUST_ID  //고객ID
     * @param TRAN_DT  //거래일자
     * @param TRAN_TM  //거래시간
     * @param TRAN_DIV //거래구분
     */
    protected void checkBalance(String OGN_CD, String CUST_ID, String TRAN_DT, String
            TRAN_TM, String TRAN_DIV) {

        Call<ResponseBody> data = service.get101Data(OGN_CD, CUST_ID, TRAN_DT, TRAN_TM, TRAN_DIV);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


//                    Log.i("모은",response.body().toString());

                try {
                    String result = response.body().string();
                    Log.i("모은", response.body().string());
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String OGN_CD;
                        String CUST_ID;
                        String TRAN_DT;
                        String TRAN_TM;
                        String TRAN_DIV;
                        String BAL_AMT;//잔액
                        String REPY_CD;//응답코드
                        BAL_AMT = jsonObject.getString("BAL_AMT");
                        Log.i("모은", BAL_AMT);

                    } catch (JSONException e) {
                        e.printStackTrace();

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


