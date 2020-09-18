package com.pay.tutoring.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.pay.tutoring.AppManager;
import com.pay.tutoring.MainActivity;
import com.pay.tutoring.R;
import com.pay.tutoring.student.StudentVO;
import com.pay.tutoring.network.NetworkActivity;
import com.pay.tutoring.network.NetworkStatus;
import com.pay.tutoring.network.RetrofitClient;
import com.pay.tutoring.network.ServiceApi;
import com.pay.tutoring.payment.card.ReplyCodeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {

    NetworkStatus networkStatus;
    private ServiceApi service;
    private ArrayList<StudentVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        service = RetrofitClient.getClient().create(ServiceApi.class);//내 서버 연결


        Log.d("!!!!!","loading activity");

        int networkSatusNum = networkStatus.getConnectivityStatus(getApplicationContext());
        if (networkSatusNum == networkStatus.TYPE_NOT_CONNECTED)
            goToNextActivity(new NetworkActivity());

        else{
            getStudentList("ta01");
//            goToNextActivity(new MainActivity());
        }
//            goToNextActivity(new LoginActivity());
//            goToNextActivity(new TestActivity());

    }

    private void goToNextActivity(Activity activity) {

        Log.i("모은", "goToNextActivity");
        finish();
        Intent intent = new Intent(getApplicationContext(), activity.getClass());
        startActivity(intent);
    }


    protected void getStudentList(String teacher_id) {
        Log.i("모은","들어왔음");

        Call<ResponseBody> data = service.selectStudent(teacher_id);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                try {
                    result = response.body().string();
                    Log.i("모은",result);
                    JSONArray jsonArray = null;
                    list = AppManager.getInstance().getStudentList();
                    try {

                        JSONObject jObject = new JSONObject(result);
                        jsonArray = (JSONArray) jObject.get("result");


                        String student_id;
                        String name;
                        String lectureDay;
                        String startTime;
                        String endTime;
                        int repeatDay;
                        int count;
                        int totalCount;
                        String progress;
                        int pay;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Log.i("모은","jsonarray들어옴");
                            Log.i("모은", String.valueOf(jsonArray.length()));
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            student_id = jsonObject.getString("student_id");
                            name = jsonObject.getString("name");
                            lectureDay = jsonObject.getString("lectureDay");
                            startTime = jsonObject.getString("startTime");
                            endTime = jsonObject.getString("endTime");
                            repeatDay = jsonObject.getInt("repeatDay");
                            count = jsonObject.getInt("count");
                            totalCount = jsonObject.getInt("totalCount");
                            progress = jsonObject.getString("progress");
                            pay = jsonObject.getInt("pay");
                            Log.i("모은", name);
                            Log.i("모은", lectureDay);
                            Log.i("모은", startTime);
                            Log.i("모은", student_id);
                            StudentVO studentVO = new StudentVO(student_id, name, lectureDay, startTime, endTime, repeatDay, count, totalCount, progress,pay);
                            list.add(studentVO);
                            Log.i("모은", String.valueOf(list.size()));


                        }

                        AppManager.getInstance().setStudentList(list);
                        Log.d("!!!!!","loading activity list length:"+list.size());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }finally{
                        goToNextActivity(new MainActivity());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("모은","못 들어와...");

            }
        });


    }

    protected void checkBalance(String OGN_CD, String CUST_ID, String TRAN_DT, String TRAN_TM, String TRAN_DIV) {

        Call<ResponseBody> data = service.get101Data(OGN_CD, CUST_ID, TRAN_DT, TRAN_TM, TRAN_DIV);
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