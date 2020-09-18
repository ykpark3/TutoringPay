package com.pay.tutoring.student;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.R;

import java.util.List;

public class AddingStudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StudentsListAdapter.StudentInfo> studentsList;
    private List<LectureTime> lectureTimeList;

    public AddingStudentsAdapter(List<LectureTime> lectureTimeList) {
        this.lectureTimeList = lectureTimeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {

        View view = null;
        Context context = parent.getContext();

        Handler handler = new Handler();

        LayoutInflater inflaterHeader = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflaterHeader.inflate(R.layout.add_time, parent, false);
        ListHeaderViewHolder viewHolder = new ListHeaderViewHolder(view);

        return viewHolder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final LectureTime lectureTime = lectureTimeList.get(position);
        final ListHeaderViewHolder lectureTieController = (ListHeaderViewHolder) holder;

        lectureTieController.from.setText(lectureTime.startTime);
        lectureTieController.to.setText(lectureTime.endTime);

        switch(lectureTime.dayOfWeek){
            case 2:
                lectureTieController.week.setText("월");
                break;

            case 3:
                lectureTieController.week.setText("화");
                break;

            case 4:
                lectureTieController.week.setText("수");
                break;

            case 5:
                lectureTieController.week.setText("목");
                break;

            case 6:
                lectureTieController.week.setText("금");
                break;

            case 7:
                lectureTieController.week.setText("토");
                break;

            case 1:
                lectureTieController.week.setText("일");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lectureTimeList.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView week;
        public TextView from;
        public TextView to;

        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            week = (TextView) itemView.findViewById((R.id.week));
            from = (TextView) itemView.findViewById((R.id.from_time));
            to = (TextView) itemView.findViewById((R.id.to_time));
        }
    }

    public static class LectureTime {

        public int dayOfWeek;
        public String startTime;
        public String endTime;

        public LectureTime(int dayOfWeek, String startTime, String endTime) {

            this.dayOfWeek = dayOfWeek;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}