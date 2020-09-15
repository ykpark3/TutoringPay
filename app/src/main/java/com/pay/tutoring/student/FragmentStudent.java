package com.pay.tutoring.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentStudent extends Fragment {

    private RecyclerView recyclerview;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();  // 데이터를 담을 List

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "손모은"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "월,수",8,"01049075608"));

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "이윤환"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "월,수",4,"01049075608"));

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "박윤경"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "월,수",8,"01049075608"));



//
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "정상벽"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "축구"));
//
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "김채운"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "축구"));
//
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "박태순"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "축구"));
        recyclerview.setAdapter(new ExpandableListAdapter(data));

        return view;
    }
}
