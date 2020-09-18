package com.pay.tutoring.calendar;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.AppManager;
import com.pay.tutoring.R;
import com.pay.tutoring.student.StudentVO;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
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

    Calendar calendar;
    CalendarDay calendarDay;
    ArrayList<CalendarDay> dates;

    private ArrayList<ClassData> classList;   // 수업 목록
    private ArrayList<ClassData> todayClassList;   // 표시할 목록
    private ClassAdapter mAdapter;
    private ArrayList<StudentVO> studentList;   // 전체 수업 목록


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentList = AppManager.getInstance().getStudentList();

        classList = new ArrayList<>();

        Log.d("!!!!!", "fragment calendar");
        Log.d("!!!!!", "studentList size???: " + String.valueOf(studentList.size()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);


        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        todayClassList = new ArrayList<>();

        mAdapter = new ClassAdapter(todayClassList, getActivity());
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter.notifyDataSetChanged();


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


        Log.d("!!!!!", "studentList size: " + String.valueOf(studentList.size()));


        new ApiSimulator(studentList).executeOnExecutor(Executors.newSingleThreadExecutor());


        // 클릭 이벤트
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                Log.d("!!!!!date test ", String.valueOf(date));

                todayClassList.clear();

                for (ClassData classData : classList) {
                    if (date.equals(classData.getDate())) {
                        ClassData data = new ClassData(classData.getName(), date, classData.getTime(), classData.getCount(), "-");

                        todayClassList.add(data);
                    }

                }
                mAdapter.notifyDataSetChanged();

                materialCalendarView.clearSelection();
            }
        });

        return view;
    }


    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
        ArrayList<StudentVO> studentList;

        ApiSimulator(ArrayList<StudentVO> studentList) {
            this.studentList = studentList;
        }


        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d("!!!!!", "doInBackground");

            calendar = Calendar.getInstance();

            dates = new ArrayList<>();   // 점 찍는 거 추가

            for (StudentVO student : studentList) {

                String[] time = student.getLectureDay().split("\\.");
                Log.d("!!!!!", String.valueOf(time.length));

                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

                calendar.set(year, month - 1, day);
                calendarDay = CalendarDay.from(calendar);
                dates.add(calendarDay);

                Log.d("!!!!!","calendarDay:"+ calendarDay);

                ClassData data = new ClassData(student.getName(), calendarDay,student.getStartTime() + "~" + student.getEndTime(), student.getCount() + "/" + student.getTotalCount(), student.getProgress());
                classList.add(data);

                makeDot(student, student.getRepeatDay());

                Log.d("!!!!!","calendarDay:"+ String.valueOf(calendarDay));
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            Log.d("!!!!!", "onPostExecute");
            /*
            if (getActivity().isFinishing()) {
                return;
            }

             */

            materialCalendarView.addDecorator(new EventDecorator(Color.RED, calendarDays, getActivity()));
        }
    }

    private void makeDot(StudentVO student, int repeatDay) {
        int count = student.getCount();

        while (count <= student.getTotalCount()) {
            calendar.set(Calendar.DAY_OF_WEEK,repeatDay);
            calendar.add(Calendar.DATE, 7);
            calendar.set(Calendar.DAY_OF_WEEK, repeatDay);
            calendarDay = CalendarDay.from(calendar);

            Log.d("!!!!!", "count"+ count + student.getName()+ String.valueOf(calendarDay));
            ClassData data = new ClassData(student.getName(), calendarDay,student.getStartTime() + "~" + student.getEndTime(), count + "/" + student.getTotalCount(), student.getProgress());
            classList.add(data);
            dates.add(calendarDay);

            count++;
        }
    }

}