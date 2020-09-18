package com.pay.tutoring.calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.pay.tutoring.R;

public class DateTimeDialog {

    Context context;

    private Button mPositiveButton;
    private Button mNegativeButton;

    //private TextView mTxtDate, mTxtTime;

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;


    public DateTimeDialog(Context context) {
        this.context = context;
    }


    public void callFunction() {

        Log.d("!!!!!", "dialog");

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dialog = new Dialog(context);

        /*
        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.date_time_dialog);

         */


        dialog.setContentView(R.layout.date_time_dialog);
        dialog.show();

        mPositiveButton = dialog.findViewById(R.id.pbutton);
        mNegativeButton = dialog.findViewById(R.id.nbutton);

        mDatePicker = dialog.findViewById(R.id.datePicker);
        mTimePicker = dialog.findViewById(R.id.timePicker);

        //mTxtDate = dialog.findViewById(R.id.date);
        //mTxtTime = dialog.findViewById(R.id.time);



        /*
        Calendar calendar = Calendar.getInstance();
        mDatePicker.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));

         */

        /*
        mDatePicker.init(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override

            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mTxtDate.setText(String.format("%d/%d/%d", year, monthOfYear + 1, dayOfMonth));

                int hour, min;
                hour = mTimePicker.getHour();
                min = mTimePicker.getMinute();
                mTxtTime.setText(String.format("%d:%d", hour, min));

            }

        });

         */


        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            //버튼 클릭시 DatePicker로부터 값을 읽어와서 Toast메시지로 보여준다.

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                String result = String.format("%d년 %d월 %d일 %d:%d로 변경되었습니다.", mDatePicker.getYear(), mDatePicker.getMonth() + 1, mDatePicker.getDayOfMonth(),
                        mTimePicker.getHour(), mTimePicker.getMinute());
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }

        });

        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dialog.dismiss();
            }
        });

    }
}

