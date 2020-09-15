package com.pay.tutoring.network;



import com.pay.tutoring.data.InputData_101;
import com.pay.tutoring.data.LoginData;
import com.pay.tutoring.data.LoginResponse;
import com.pay.tutoring.data.OutputData_101;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import okhttp3.RequestBody;
import retrofit2.http.Query;


public interface ServiceApi {
    //user 정보
//    @POST("/user/getUserData")
//    Call<LoginResponse> getUserData(@Body LoginData data);
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);
    @POST("/user/selectStudent")
    Call<ResponseBody> selectStudent(@Query("teacher_id") String teacher_id);

    @POST("/HKT_API_101.jct")
    Call<ResponseBody> get101Data(@Query("OGN_CD") String OGN_CD,@Query("CUST_ID") String CUST_ID,@Query("TRAN_DT") String TRAN_DT,@Query("TRAN_TM") String TRAN_TM,@Query("TRAN_DIV") String TRAN_DIV );



}