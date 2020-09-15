package com.pay.tutoring.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {


    @SerializedName("cust_id")//mysql에서 자동증가로 나중에 빼기
    int cust_id;
    @SerializedName("name")
    String name;
    @SerializedName("socket_id")
    String socket_id;
    @SerializedName("phone")
    String phone;
    @SerializedName("registrationNum")
    String registrationNum;


    public LoginData(int cust_id, String name, String socket_id) {

        this.cust_id = cust_id;
        this.name = name;
        this.socket_id = socket_id;
//        this.phone = phone;
//        this.registrationNum = registrationNum;

    }



}