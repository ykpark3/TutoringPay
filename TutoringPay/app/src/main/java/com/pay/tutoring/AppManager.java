package com.pay.tutoring;

import com.pay.tutoring.data.CustVO;
import com.pay.tutoring.student.StudentVO;
import com.pay.tutoring.payment.card.AccountVO;
import com.pay.tutoring.payment.card.BankCodeList;
import com.pay.tutoring.payment.card.ReplyCodeList;

import java.util.ArrayList;

public class AppManager {
    private static AppManager instance = null;
    private CustVO custVO = new CustVO();
    private AccountVO currentTransfer = new AccountVO();
    private ArrayList<StudentVO> studentList = new ArrayList<>();
    private BankCodeList bankCodeList = new BankCodeList();
    private ReplyCodeList replyCodeList = new ReplyCodeList();

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    public void setCustVO(CustVO custVO){this.custVO = custVO;}
    public CustVO getCustVO(){return custVO;}

    public void setCurrentTransfer(AccountVO currentTransfer){this.currentTransfer = currentTransfer;}
    public AccountVO getCurrentTransfer(){return currentTransfer;}

    public void setStudentList(ArrayList<StudentVO> studentList) {
        this.studentList = studentList;
    }
    public ArrayList<StudentVO> getStudentList() {
        return studentList;
    }

    public void setBankCodeList(BankCodeList bankCodeList) {
        this.bankCodeList = bankCodeList;
    }
    public BankCodeList getBankCodeList() {
        return bankCodeList;
    }

    //로그인 정보 저장
    //로그인 세션 관련해서 알아보기

}

//규칙

//drawable
//icon 의 경우 ic_ 로 시작
//border xml은 border_로 시작
//bank img > bank_로 시작
//어디에 쓰이는지 확실한 이미지의 경우 activity이름_이미지형식 ex) loading_logo