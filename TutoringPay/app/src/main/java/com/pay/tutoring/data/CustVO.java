package com.pay.tutoring.data;

public class CustVO {

    String name;
    int phone;
    int companyRegistrationNumber;
    String bank;//banklist만들어서 bank_code저장하고 나중에 이거랑 비교 or 여기다가 bank_code로 넣게하기
    int accountNum;
    String socketId;

    public CustVO()
    {
        this.name = "";
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
}

