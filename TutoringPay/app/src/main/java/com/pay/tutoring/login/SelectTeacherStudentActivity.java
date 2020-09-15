package com.pay.tutoring.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pay.tutoring.MainActivity;
import com.pay.tutoring.R;

public class SelectTeacherStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_teacher_student);

        // BtnOnClickListener의 객체 생성.
        BtnOnClickListener onClickListener = new BtnOnClickListener() ;

        //각 Button의 이벤트 리스터로 onClickListener 지정
        Button btn_teacher = (Button) findViewById(R.id.btn_teacher);
        Button btn_student = (Button) findViewById(R.id.btn_student);
        btn_teacher.setOnClickListener(onClickListener);
        btn_student.setOnClickListener(onClickListener);

    }
    class BtnOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            // 일단은 intent로 넘기는 걸로 하고, 서버 되면 바꿔놓기
            finish();
            Intent intent = new Intent(SelectTeacherStudentActivity.this, MainActivity.class);

            if(view.getId() == R.id.btn_teacher)
            {
                intent.putExtra("user", "teacher");
            }
            else{
                intent.putExtra("user", "student");
            }

            startActivity(intent);
        }
    }
}