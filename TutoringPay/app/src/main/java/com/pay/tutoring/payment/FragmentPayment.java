package com.pay.tutoring.payment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.pay.tutoring.MainActivity;
import com.pay.tutoring.R;
import com.github.mikephil.charting.data.Entry;
import com.pay.tutoring.card.CardActivity;
import com.pay.tutoring.login.AgreePersonalInfortmation;
import com.pay.tutoring.login.SelectTeacherStudentActivity;
import com.pay.tutoring.payment.card.TransferActivity;

import java.util.ArrayList;
import java.util.Collections;

public class FragmentPayment extends Fragment {
    public FragmentPayment() {
    }




    public static FragmentPayment newInstance() {
        FragmentPayment fragment = new FragmentPayment();
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
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        LineChart lineChart = view.findViewById(R.id.lineChart);

        ArrayList<Entry> totalEntry = new ArrayList<>();
        ArrayList<Entry> receivedEntry = new ArrayList<>();

        totalEntry.add(new Entry(1, 700000));
        totalEntry.add(new Entry(2, 900000));
        totalEntry.add(new Entry(3, 800000));
        totalEntry.add(new Entry(4, 110000));

        receivedEntry.add(new Entry(1, 700000));
        receivedEntry.add(new Entry(2, 900000));
        receivedEntry.add(new Entry(3, 800000));
        receivedEntry.add(new Entry(4, 700000));

        LineDataSet totalDataset = new LineDataSet(totalEntry, "총 금액");
        LineDataSet receivedDataset = new LineDataSet(receivedEntry, "받은 금액");

        totalDataset.setColors(Collections.singletonList(Color.parseColor("#6699FF")));
        receivedDataset.setColors(Collections.singletonList(Color.parseColor("#A366FF")));

        /*
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("6월");
        labels.add("7월");
        labels.add("8월");
        labels.add("9월");

         */
        LineData lineData = new LineData();

        XAxis xAxis = lineChart.getXAxis();   // x축 설정
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);   // x축 표시 위치 설정

        String[] month = {"","6월","7월","8월","9월"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(month));

        // 오른쪽 y축 비활성화
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        lineData.addDataSet(totalDataset);
        lineData.addDataSet(receivedDataset);

        lineChart.setDescription(null);   // 차트에서 Description 설정
        lineChart.setData(lineData);
        lineChart.animateY(500);

        lineChart.invalidate();

        // BtnOnClickListener의 객체 생성.
        FragmentPayment.BtnOnClickListener onClickListener = new BtnOnClickListener();

        //각 Button의 이벤트 리스터로 onClickListener 지정
        Button btn_tax = (Button) view.findViewById(R.id.transfer);
        btn_tax.setOnClickListener(onClickListener);


        return view;
    }
    class BtnOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            // 일단은 intent로 넘기는 걸로 하고, 서버 되면 바꿔놓기
            switch (view.getId())
            {
                case R.id.transfer:
                    Intent intent = new Intent(getContext(), TransferActivity.class);
//                    intent.putExtra("setting", "login");
                    startActivity(intent);
                    break;
            }
        }
    }
}
