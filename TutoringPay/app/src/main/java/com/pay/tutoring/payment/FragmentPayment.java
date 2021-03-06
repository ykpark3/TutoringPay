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

        LineDataSet totalDataset = new LineDataSet(totalEntry, "??? ??????");
        LineDataSet receivedDataset = new LineDataSet(receivedEntry, "?????? ??????");

        totalDataset.setColors(Collections.singletonList(Color.parseColor("#6699FF")));
        receivedDataset.setColors(Collections.singletonList(Color.parseColor("#A366FF")));

        /*
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("6???");
        labels.add("7???");
        labels.add("8???");
        labels.add("9???");

         */
        LineData lineData = new LineData();

        XAxis xAxis = lineChart.getXAxis();   // x??? ??????
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);   // x??? ?????? ?????? ??????

        String[] month = {"","6???","7???","8???","9???"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(month));

        // ????????? y??? ????????????
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        lineData.addDataSet(totalDataset);
        lineData.addDataSet(receivedDataset);

        lineChart.setDescription(null);   // ???????????? Description ??????
        lineChart.setData(lineData);
        lineChart.animateY(500);

        lineChart.invalidate();

        // BtnOnClickListener??? ?????? ??????.
        FragmentPayment.BtnOnClickListener onClickListener = new BtnOnClickListener();

        //??? Button??? ????????? ???????????? onClickListener ??????
        Button btn_tax = (Button) view.findViewById(R.id.transfer);
        btn_tax.setOnClickListener(onClickListener);


        return view;
    }
    class BtnOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            // ????????? intent??? ????????? ?????? ??????, ?????? ?????? ????????????
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
