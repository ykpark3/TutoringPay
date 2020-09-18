package com.pay.tutoring.payment.card;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pay.tutoring.AppManager;
import com.pay.tutoring.R;

import java.util.ArrayList;
//1
public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<AccountVO> list = new ArrayList<>();

        list.add(new AccountVO("손모은", "국민", 1234567890, R.drawable.bank_kb_logo));
        list.add(new AccountVO("손모은", "우리", 1234567890, R.drawable.bank_kb_logo));


        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.list_account);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        AccountListAdapter adapter = new AccountListAdapter(list);
        adapter.setOnItemClickListener(new AccountListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                AccountVO currentTransfer = AppManager.getInstance().getCurrentTransfer();

                currentTransfer.setName(adapter.getItem(position).getName());
                currentTransfer.setBank(adapter.getItem(position).getBank());
                currentTransfer.setAccountNum(adapter.getItem(position).getAccountNum());
                currentTransfer.setImg(adapter.getItem(position).getImg());

                finish();
                Intent intent = new Intent(getApplicationContext(), EnterAmountActivity.class);
                startActivity(intent);


            }
        });
        recyclerView.setAdapter(adapter);


    }


}