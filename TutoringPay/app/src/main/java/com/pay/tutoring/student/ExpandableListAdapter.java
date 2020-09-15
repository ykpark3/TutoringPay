package com.pay.tutoring.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private List<Item> data;

    public ExpandableListAdapter(List<Item> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;
        Context context = parent.getContext();
        switch (type) {
            case HEADER:
                LayoutInflater inflaterHeader = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflaterHeader.inflate(R.layout.list_header, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;
            case CHILD:
                LayoutInflater inflaterChild = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflaterChild.inflate(R.layout.list_child, parent, false);
                ListChildViewHolder child = new ListChildViewHolder(view);
                return child;
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.StudentName.setText(item.name);
//                itemController.StudentName.setText(item.time);
//                itemController.StudentName.setText(Integer.toString(item.totalCount));
//                itemController.StudentName.setText(item.phoneNumber);

                if (item.invisibleChildren == null) {
                    itemController.profileImage.setImageResource(R.drawable.unannn);
                } else {
                    itemController.profileImage.setImageResource(R.drawable.unannn);
                }
                itemController.profileImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);
                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.profileImage.setImageResource(R.drawable.unannn);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.profileImage.setImageResource(R.drawable.unannn);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;

            case CHILD:
                final ListChildViewHolder itemController1 = (ListChildViewHolder) holder;
                itemController1.refferalItem = item;
                itemController1.time.setText(item.time);
                itemController1.totalCount.setText(Integer.toString(item.totalCount));
                itemController1.phoneNumber.setText(item.phoneNumber);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImage;
        public TextView StudentName;
        public Item refferalItem;

        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.profile_image);
            StudentName = (TextView) itemView.findViewById(R.id.stu_name);
        }
    }
    private static class ListChildViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView totalCount;
        public TextView phoneNumber;

        public Button btn;
        public Item refferalItem;

        public ListChildViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            totalCount = (TextView) itemView.findViewById(R.id.totalCount);
            phoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber);

            btn =  itemView.findViewById(R.id.btn);
        }
    }

    public static class Item {
        public int type;
        public String name;
        public String time;
        public int totalCount;
        public int count;
        public String phoneNumber;

        public List<Item> invisibleChildren;

        public Item(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public Item(int type, String time, int totalCount, String phoneNumber) {
            this.type = type;
            this.time = time;
            this.totalCount = totalCount;
            this.count = 0;
            this.phoneNumber = phoneNumber;
        }
    }
}