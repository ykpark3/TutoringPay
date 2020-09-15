package com.pay.tutoring.card;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pay.tutoring.R;
import com.pay.tutoring.payment.FragmentPayment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class FragmentCard extends Fragment {
    GridView gridView;
    GridAdapter adapter;
    ImageButton back_btn;
    ArrayList<CardVO> cards = new ArrayList<>();

    public FragmentCard() {
    }

    public static FragmentCard newInstance() {
        FragmentCard fragment = new FragmentCard();
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
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        cards.add(new CardVO("NH농협",R.drawable.bank_kb_logo));
        cards.add(new CardVO("우리",R.drawable.bank_kb_logo));
        cards.add(new CardVO("신한",R.drawable.bank_kb_logo));
        cards.add(new CardVO("KB국민",R.drawable.bank_kb_logo));
        cards.add(new CardVO("하나",R.drawable.bank_kb_logo));
        cards.add(new CardVO("씨티",R.drawable.bank_kb_logo));
        cards.add(new CardVO("IBK기업",R.drawable.bank_kb_logo));
        cards.add(new CardVO("케이뱅크",R.drawable.bank_kb_logo));
        cards.add(new CardVO("카카오뱅크",R.drawable.bank_kb_logo));


        adapter = new GridAdapter(getContext(), cards);
        gridView = view.findViewById(R.id.cardGrid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(itemClickListener);
        back_btn = view.findViewById(R.id.select_card_back_btn);
        return view;
    }
    private GridView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            ((CardActivity)getActivity()).replaceFragment(FragmentAccountNumber.newInstance());
//            Intent intent = new Intent(getContext(), AgreementActivity.class);
//            intent.putExtra("card", i);
//            startActivity(intent);
        }


    };

}
