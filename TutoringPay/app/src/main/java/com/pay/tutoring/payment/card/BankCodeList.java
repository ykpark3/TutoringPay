package com.pay.tutoring.payment.card;

import java.util.HashMap;
import java.util.Map;

public class BankCodeList {

    Map<String,Object> bankMap = new HashMap<String,Object>();

    public BankCodeList(){
        //나중에 txt파일로 관리하기(removedBankList)
        bankMap.put("002","산업은행");
        bankMap.put("003","기업은행");
        bankMap.put("004","국민은행");
        bankMap.put("005","KEB하나은행(구 외환은행)");
        bankMap.put("007","수협은행");
        bankMap.put("008","수출입은행");
        bankMap.put("011","농협은행");
        bankMap.put("020","우리은행");
        bankMap.put("023","SC제일은행");
        bankMap.put("027","씨티은행");
        bankMap.put("031","대구은행");
        bankMap.put("032","부산은행");
        bankMap.put("034","광주은행");
        bankMap.put("035","제주은행");
        bankMap.put("037","전북은행");
        bankMap.put("039","경남은행");
        bankMap.put("045","새마을금고");
        bankMap.put("048","신협");
        bankMap.put("050","상호저축은행");
        bankMap.put("054","HSBC");
        bankMap.put("055","도이치");
        bankMap.put("057","JP모간");
        bankMap.put("060","BOA");
        bankMap.put("061","비엔피파리바은행");
        bankMap.put("062","중국공상은행");
        bankMap.put("064","산림조합");
        bankMap.put("071","우체국");
        bankMap.put("081","하나은행");
        bankMap.put("088","신한은행");
        bankMap.put("089","K뱅크");
        bankMap.put("090","카카오뱅크");
        bankMap.put("209","유안타증권");
        bankMap.put("218","현대증권");
        bankMap.put("230","미래에셋");
        bankMap.put("238","대우증권");
        bankMap.put("240","삼성증권");
        bankMap.put("243","한국투자");
        bankMap.put("247","NH투자증권");
        bankMap.put("261","교보증권");
        bankMap.put("262","하이투자");
        bankMap.put("263","HMC증권");
        bankMap.put("264","키움증권");
        bankMap.put("265","이베스트증권");
        bankMap.put("266","SK증권");
        bankMap.put("267","대신증권");
        bankMap.put("268","아이엠투자증권");
        bankMap.put("269","한화증권");
        bankMap.put("270","하나대투");
        bankMap.put("278","신한금융투자");
        bankMap.put("279","동부증권");
        bankMap.put("280","유진투자증권");
        bankMap.put("287","메리츠증권");
        bankMap.put("290","부국증권");
        bankMap.put("291","신영증권");
        bankMap.put("292","LIG투자증권");

    }

    public String getBankName(String str)
    {
        String name = (String) bankMap.get(str);
        return name;
    }
}
