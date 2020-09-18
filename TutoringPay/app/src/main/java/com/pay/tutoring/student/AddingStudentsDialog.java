package com.pay.tutoring.student;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.pay.tutoring.R;

import java.util.ArrayList;
import java.util.List;

public class AddingStudentsDialog extends Dialog {

    private Context context;

    private int dayOfWeek;
    private RecyclerView addingStudentsView;
    private Button addingTimeButton;
    private EditText inputPay;
    private TextInputEditText inputMemo;
    private Button addingStudentButton;
    private Spinner dayList;
    private Spinner fromTime;
    private Spinner toTime;

    private List<StudentsListAdapter.StudentInfo> studentData;
    private List<AddingStudentsAdapter.LectureTime> studentTimeData;

    public AddingStudentsDialog(@NonNull Context context, List<StudentsListAdapter.StudentInfo> studentData) {
        super(context);
        this.context = context;
        this.studentData = studentData;
        studentTimeData = new ArrayList<AddingStudentsAdapter.LectureTime>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_students);

        addingStudentsView = findViewById(R.id.add_time);
        dayList = findViewById(R.id.week);
        fromTime = findViewById(R.id.from_time);
        toTime = findViewById(R.id.to_time);
        dayList = findViewById(R.id.week);
        addingTimeButton = findViewById(R.id.add_time_button);
        inputPay = findViewById(R.id.input_pay);
        inputMemo = findViewById(R.id.input_memo);
        addingStudentButton = findViewById(R.id.input_add_button);
        String[] weekList = context.getResources().getStringArray(R.array.week_list);
        String[] timeList = context.getResources().getStringArray(R.array.time_list);


        addingStudentsView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        //final List<AddingStudentsAdapter.Item> data = new ArrayList<>();  // 데이터를 담을 List


        ArrayAdapter weekAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, weekList);
        ArrayAdapter timeAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, timeList);

        dayList.setAdapter(weekAdapter);
        dayList.setSelection(0);

        fromTime.setAdapter((timeAdapter));
        fromTime.setSelection(0);
        toTime.setAdapter((timeAdapter));
        toTime.setSelection(0);

        addingTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                studentTimeData.add(new AddingStudentsAdapter.LectureTime(findDayOfWeek(dayList.getSelectedItemPosition()),fromTime.getSelectedItem().toString(),toTime.getSelectedItem().toString()));
                addingStudentsView.setAdapter(new AddingStudentsAdapter(studentTimeData));
            }
        });


        addingStudentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //Dialog dialog = new AddingStudentsDialog(context, data);
                //dialog.show();

                //data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "정상벽"));
                //data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "월,수",8,"01049075608"));
                //recyclerview.setAdapter(new ExpandableListAdapter(data));

            }
        });

        addingStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                //데이터베이스에 정보 입력
//                if(!inputId.getText().toString().equals("")){
//                    Log.d("inpuid", "a"+ inputId.getText().toString() + "a");
//
//                    dismiss();   //다이얼로그를 닫는 메소드입니다.
//                }else{
//                    Log.d("버튼", "onClick: 아무것도 입려되지 않음");
//                }

            }
        });
    }

    private int findDayOfWeek(int position){
        int dayOfWeekNumber = 0;
        switch (position) {
            case 0:
                dayOfWeekNumber = 2;
                break;

            case 1:
                dayOfWeekNumber = 3;
                break;

            case 2:
                dayOfWeekNumber = 4;
                break;

            case 3:
                dayOfWeekNumber = 5;
                break;

            case 4:
                dayOfWeekNumber = 6;
                break;

            case 5:
                dayOfWeekNumber = 7;
                break;

            case 6:
                dayOfWeekNumber = 1;
                break;
        }

        return dayOfWeekNumber;

    }
}
