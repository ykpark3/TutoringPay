package com.pay.tutoring.calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.CustomViewHolder> {

    private ArrayList<ClassData> mList;
    Context context;
    int selectedProgress;

    public ClassAdapter (ArrayList<ClassData> list, Context context) {
        this.mList = list;
        this.context = context;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView time;
        protected TextView count;
        protected Button progress;


        public CustomViewHolder(View view) {
            super(view);

            this.name = (TextView) view.findViewById(R.id.name);
            this.time = (TextView) view.findViewById(R.id.time);
            this.count = (TextView) view.findViewById(R.id.count);
            this.progress = (Button) view.findViewById(R.id.progress);


            progress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("!!!!!","click");

                    final String[] progressCondition = {"O", "→","X"};


                    AlertDialog.Builder dialog = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                    dialog.setTitle("수업 진행 상태를 선택하세요.").setSingleChoiceItems(progressCondition, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Log.d("!!!!!","선택");
                            selectedProgress = which;
                            //nSelectItem = which;
                        }
                    })
                            .setNeutralButton("선택", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Log.d("!!!!!","선택 완료");

                                    if(selectedProgress == 1) {

                                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener, 2013, 9, 22);
                                        datePickerDialog.show();

                                    }

                                    progress.setText(progressCondition[selectedProgress]);


                                    /*
                                    if (which >= 0)
                                        Toast.makeText(getApplicationContext(),
                                                oItems[nSelectItem], Toast.LENGTH_LONG).show();

                                     */
                                }
                            })
                            .setCancelable(false)
                            .show();

                }
            });
        }
    }




    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.class_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        /*
        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        viewholder.time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        viewholder.count.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        viewholder.progress.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.time.setGravity(Gravity.CENTER);
        viewholder.count.setGravity(Gravity.CENTER);
        viewholder.progress.setGravity(Gravity.CENTER);

         */


        Log.d("!!!!!","이름:  "+mList.get(position).getName());
        viewholder.name.setText(mList.get(position).getName());
        viewholder.time.setText(mList.get(position).getTime());
        viewholder.count.setText(mList.get(position).getCount());
        viewholder.progress.setText(mList.get(position).getProgress());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


    public void datePicker() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(context, year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
            }
        };
    }
}