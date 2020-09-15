package com.pay.tutoring;

import com.pay.tutoring.data.CustVO;
import com.pay.tutoring.data.StudentVO;

import java.util.ArrayList;

public class AppManager {
    private static AppManager instance = null;
    private CustVO custVO = new CustVO();
    private StudentVO studentVO;
    private ArrayList<StudentVO> studentList = new ArrayList<>();
    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    public void setCustVO(CustVO custVO){this.custVO = custVO;}
    public CustVO getCustVO(){return custVO;}

    public void setStudentList(ArrayList<StudentVO> studentList) {
        this.studentList = studentList;
    }
    public ArrayList<StudentVO> getStudentList() {
        return studentList;
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