package com.pay.tutoring.student;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pay.tutoring.AppManager;
import com.pay.tutoring.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentStudent extends Fragment {

    private RecyclerView studentsList;
    private FloatingActionButton addButton;
    public FragmentStudent() {
    }

    public static FragmentStudent newInstance() {
        FragmentStudent fragment = new FragmentStudent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student, container, false);

        studentsList = view.findViewById(R.id.students_list);
        studentsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        final List<StudentsListAdapter.StudentInfo> studentList = new ArrayList<>();  // 데이터를 담을 List
        ArrayList<StudentVO> list = AppManager.getInstance().getStudentList();
        for(StudentVO student : list)
        {
            Log.i("모은", student.getName());
        }
        Log.i("윤환",list.get(0).getName());
        addButton = view.findViewById(R.id.fab);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                IdSearchDialog dialog = new IdSearchDialog(getActivity(), studentList);
                dialog.show();

                //data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "정상벽"));
                //data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "월,수",8,"01049075608"));
                //recyclerview.setAdapter(new ExpandableListAdapter(data));

            }
        });

        studentsList.setAdapter(new StudentsListAdapter(studentList));

        return view;
    }
}
