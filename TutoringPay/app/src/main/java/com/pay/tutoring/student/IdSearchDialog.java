package com.pay.tutoring.student;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.pay.tutoring.R;

import java.util.List;

public class IdSearchDialog extends Dialog {
    private Context context;
    private TextView inputId;
    private Button button;
    private List<StudentsListAdapter.StudentInfo> studentList;

    public IdSearchDialog(@NonNull Context context, List<StudentsListAdapter.StudentInfo> studentList) {
        super(context);
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_id);

        inputId = findViewById(R.id.input_id_content);
        button = findViewById(R.id.input_id_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터베이스 에서 아이디 조회

                //Log.d("inpuid", "a"+inputId.getText().toString());
                if(!inputId.getText().toString().equals("")){

                    AddingStudentsDialog AddingStudentdialog = new AddingStudentsDialog(context, studentList);
                    AddingStudentdialog.show();

//                    data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "정상벽"));
//                    data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "월,수",8,"01049075608"));
                    dismiss();   //다이얼로그를 닫는 메소드입니다.
                }else{
                    Log.d("버튼", "onClick: 아무것도 입려되지 않음");
                }

            }
        });
    }
}
