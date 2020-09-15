package com.pay.tutoring.calendar;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.MainActivity;
import com.pay.tutoring.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.List;
import java.util.concurrent.Executors;

public class FragmentCalendar extends Fragment {
    public FragmentCalendar() {
    }

    public static FragmentCalendar newInstance() {
        FragmentCalendar fragment = new FragmentCalendar();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    MaterialCalendarView materialCalendarView;

    private ArrayList<ClassData> mArrayList;
    private ClassAdapter mAdapter;
    private int count = 0;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);


        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new ClassAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        Button buttonInsert = view.findViewById(R.id.button_main_insert);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                Log.d("!!!!!","추가");
                ClassData data = new ClassData(count+"유재석","11:30~13:00","2/8","O");

                //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

                Log.d("!!!!!!","arraylist size:"+String.valueOf(mArrayList.size()));
                mAdapter.notifyDataSetChanged();
            }
        });





        materialCalendarView = view.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)   // 일~토 순으로
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)   // '월'로 표시
                .commit();

        // 달력 효과
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        String[] result = {"2020,08,01","2020,09,01","2020,10,01","2020,11,01"};
        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        // 클릭 이벤트
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = Year + "," + Month + "," + Day;

                Log.i("!!!!!shot_Day test ", shot_Day + "");
                materialCalendarView.clearSelection();

            }
        });


        // 달력이 변화할 때 이벤트
        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });

        return view;
    }


    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d("!!!!!","doInBackground");

            Calendar calendar = Calendar.getInstance();
            //calendar.add(Calendar.MONTH, -2);   // 2달 전부터

            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*
            특정날짜 달력에 표시
            월은 0이 1월 년,일은 그대로
            string 문자열인 Time_Result 을 받아와서 ,를 기준으로 자르고 string을 int로 변환
             */

            Log.d("!!!!!","타임 길이 "+String.valueOf(Time_Result.length));

            for(int i = 0 ; i < Time_Result.length ; i ++){

                Log.d("!!!!!","타임   "+ i+ "   " +Time_Result[i]);

                String[] time = Time_Result[i].split(",");

                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                calendar.set(year,month-1,dayy);

                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);

                Log.d("!!!!!","day "+ "   " +day);
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            Log.d("!!!!!","onPostExecute");
            if (getActivity().isFinishing()) {
                return;
            }

            materialCalendarView.addDecorator(new EventDecorator(Color.RED, calendarDays, getActivity()));
        }
    }

    /*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("!!!!!","onViewCreated");


     */
}
