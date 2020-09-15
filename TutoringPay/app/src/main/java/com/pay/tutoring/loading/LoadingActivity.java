package com.pay.tutoring.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.pay.tutoring.AppManager;
import com.pay.tutoring.MainActivity;
import com.pay.tutoring.R;
import com.pay.tutoring.data.StudentVO;
import com.pay.tutoring.login.AgreePersonalInfortmation;
import com.pay.tutoring.login.LoginActivity;
import com.pay.tutoring.network.NetworkActivity;
import com.pay.tutoring.network.NetworkStatus;
import com.pay.tutoring.network.RetrofitClient;
import com.pay.tutoring.network.ServiceApi;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        int networkSatusNum = networkStatus.getConnectivityStatus(getApplicationContext());
        if (networkSatusNum == networkStatus.TYPE_NOT_CONNECTED)
            goToNextActivity(new NetworkActivity());

        else{
            getStudentList("ta01");
            goToNextActivity(new MainActivity());

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

    protected void getStudentList(String teacher_id) {

        Call<ResponseBody> data = service.selectStudent(teacher_id);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                try {
                    result = response.body().string();
                    Log.i("모은",result);
                    JSONArray jsonArray = null;
                    try {



//                        jsonArray = new JSONArray(result);
                        JSONObject jObject = new JSONObject(result);
                        jsonArray = (JSONArray) jObject.get("result");


                        String student_id;
                        String name;
                        String startDay;
                        String startTime;
                        String endTime;
                        String repeatDay;
                        int count;
                        int totalCount;
                        int progress;

                        ArrayList<StudentVO> list = AppManager.getInstance().getStudentList();
                        for (int i = 0; i < jsonArray.length(); i++) {
//                            Log.i("모은", String.valueOf(jsonArray.length()));
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            student_id = jsonObject.getString("student_id");
                            name = jsonObject.getString("name");
                            startDay = jsonObject.getString("startDay");
                            startTime = jsonObject.getString("startTime");
                            endTime = jsonObject.getString("endTime");
                            repeatDay = jsonObject.getString("repeatDay");
                            count = jsonObject.getInt("count");
                            totalCount = jsonObject.getInt("totalCount");
                            progress = jsonObject.getInt("progress");

//                            Log.i("모은", student_id);
                            StudentVO studentVO = new StudentVO(student_id, name, startDay, startTime, endTime, repeatDay, count, totalCount, progress);
                            list.add(studentVO);

                        }
                        AppManager.getInstance().setStudentList(list);
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