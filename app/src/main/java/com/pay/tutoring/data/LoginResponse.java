package com.pay.tutoring.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("cust_id")
    private int cust_id;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getCustId() {
        return cust_id;
    }
}