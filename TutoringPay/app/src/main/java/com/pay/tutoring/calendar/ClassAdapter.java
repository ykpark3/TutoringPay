package com.pay.tutoring.calendar;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.CustomViewHolder> {

    private ArrayList<ClassData> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView time;
        protected TextView count;
        protected TextView progress;


        public CustomViewHolder(View view) {
            super(view);

            this.name = (TextView) view.findViewById(R.id.name);
            this.time = (TextView) view.findViewById(R.id.time);
            this.count = (TextView) view.findViewById(R.id.count);
            this.progress = (TextView) view.findViewById(R.id.progress);
        }
    }

    public ClassAdapter (ArrayList<ClassData> list) {
        this.mList = list;
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

}