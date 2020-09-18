package com.pay.tutoring.payment.card;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.R;
import com.pay.tutoring.card.CardActivity;
import com.pay.tutoring.login.AgreePersonalInfortmation;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.ViewHolder>{

    private ArrayList<AccountVO> mData = null ;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        TextView bank;
        TextView accountNum;
        ImageView bank_img;

        ViewHolder(View itemView) {
            super(itemView) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if(mListener !=null) {
                            mListener.onItemClick(v, pos);
//                        notifyItemChanged(pos) ;
                        }
                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            name = itemView.findViewById(R.id.name) ;
            bank = itemView.findViewById(R.id.bank) ;
            accountNum = itemView.findViewById(R.id.account_num) ;
            bank_img = itemView.findViewById(R.id.bank_img);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    AccountListAdapter(ArrayList<AccountVO> list) {
        mData = list ;
    }
    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public AccountListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.list_account, parent, false) ;
        AccountListAdapter.ViewHolder vh = new AccountListAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(AccountListAdapter.ViewHolder holder, int position) {
        String name = mData.get(position).getName() ;
        holder.name.setText(name) ;
        String bank = mData.get(position).getBank() ;
        holder.bank.setText(bank) ;
//        int accountNum = mData.get(position).getAccountNum() ;
//        holder.accountNum.setText(accountNum) ;
//        int bank_img = mData.get(position).getImg() ;
//        holder.bank_img.setImageResource(bank_img); ;

//        holder.bank_img.setImageResource();
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public AccountVO getItem(int position){ return mData.get(position); }


}
