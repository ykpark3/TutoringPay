package com.pay.tutoring.student;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pay.tutoring.R;

import java.util.List;

public class StudentsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER = 0;

    private List<StudentInfo> studentList;


    public StudentsListAdapter(List<StudentInfo> studentList) {
        this.studentList = studentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;
        Context context = parent.getContext();

        Handler handler = new Handler();

        switch (type) {
            case HEADER:
                LayoutInflater inflaterHeader = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflaterHeader.inflate(R.layout.list_header, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);


                return header;

//            case CHILD:
//                LayoutInflater inflaterChild = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                view = inflaterChild.inflate(R.layout.list_child, parent, false);
//                ListChildViewHolder child = new ListChildViewHolder(view);
//
//
//                return child;
        }

        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final StudentInfo studentInfo = studentList.get(position);

        final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;

        itemController.refferalItem = studentInfo;
        itemController.StudentName.setText(studentInfo.name);



//                itemController.profileImage.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (item.invisibleChildren == null) {
//                            item.invisibleChildren = new ArrayList<Item>();
//                            int count = 0;
//                            int pos = data.indexOf(itemController.refferalItem);
//                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
//                                item.invisibleChildren.add(data.remove(pos + 1));
//                                count++;
//                            }
//                            notifyItemRangeRemoved(pos + 1, count);
//                            itemController.profileImage.setImageResource(R.drawable.unannn);
//                        } else {
//                            int pos = data.indexOf(itemController.refferalItem);
//                            int index = pos + 1;
//                            for (Item i : item.invisibleChildren) {
//                                data.add(index, i);
//                                index++;
//                            }
//                            notifyItemRangeInserted(pos + 1, index - pos - 1);
//                            itemController.profileImage.setImageResource(R.drawable.unannn);
//                            item.invisibleChildren = null;
//                        }
//                    }
//                });
//            case CHILD:
//                final ListChildViewHolder itemController1 = (ListChildViewHolder) holder;
//                itemController1.refferalItem = item;
//                itemController1.time.setText(item.time);
//                itemController1.totalCount.setText(Integer.toString(item.totalCount));
//                itemController1.phoneNumber.setText(item.phoneNumber);
//                break;
//    }
    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return data.get(position).type;
//    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {

        public ImageView profileImage;
        public TextView StudentName;
        public StudentInfo refferalItem;

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
        public StudentInfo refferalItem;

        public ListChildViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            totalCount = (TextView) itemView.findViewById(R.id.totalCount);
            phoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber);

            btn = itemView.findViewById(R.id.btn);
        }
    }

    public static class StudentInfo {

        public String name;
        public String StartDay;
        public List<String> time;  //일단 스트링
        public int totalCount;
        public int count;
        public String phoneNumber;
        public int payPerhour;
        public String memo;

        public StudentInfo(String name, List<String> time, String StartDay, int totalCount, String phoneNumber) {
            this.name = name;
            this.time = time;
            this.totalCount = totalCount;
            this.count = 0;
            this.phoneNumber = phoneNumber;
        }
    }
}