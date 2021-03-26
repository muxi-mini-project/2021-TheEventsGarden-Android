package com.example.myapplication.cloudpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.homeworkHolder> {
    int i = 1;
    Context mContext;
    List<Homework> mHomeworkList = new ArrayList<>();

    private OnitemClick onitemClick;   //定义点击事件接口

    public void setOnitemClickLintener(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    public interface OnitemClick {
        void onItemClick(int position);
    }

    public HomeworkAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<Homework> list) {
        mHomeworkList = list;
    }

    @NonNull
    @Override
    public homeworkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.homework_item, parent, false);
        return new homeworkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final homeworkHolder holder, final int position) {
        Homework homework = mHomeworkList.get(position);

        final boolean[] details_Visible = new boolean[10];
        Arrays.fill(details_Visible,false);

        //显示 作业详情
        if(details_Visible[position] == true){
            holder.details.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.INVISIBLE);
            holder.ddl.setVisibility(View.INVISIBLE);
            holder.teacher.setVisibility(View.INVISIBLE);
        }
        //显示 作业主页面
        else if (details_Visible[position] == false){
            holder.details.setVisibility(View.INVISIBLE);
            holder.title.setVisibility(View.VISIBLE);
            holder.ddl.setVisibility(View.VISIBLE);
            holder.teacher.setVisibility(View.VISIBLE);
        }

        if (onitemClick != null) {
            holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //显示 作业详情
                    if(details_Visible[position] == false){
                        holder.details.setVisibility(View.VISIBLE);
                        holder.title.setVisibility(View.INVISIBLE);
                        holder.ddl.setVisibility(View.INVISIBLE);
                        holder.teacher.setVisibility(View.INVISIBLE);
                        details_Visible[position] = true;
                    }
                    //显示 作业主页面
                    else if (details_Visible[position] == true){
                        holder.details.setVisibility(View.INVISIBLE);
                        holder.title.setVisibility(View.VISIBLE);
                        holder.ddl.setVisibility(View.VISIBLE);
                        holder.teacher.setVisibility(View.VISIBLE);
                        details_Visible[position] = false;
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHomeworkList.size();
    }


    class homeworkHolder extends RecyclerView.ViewHolder {

        TextView title, ddl, teacher, details;
        ConstraintLayout mConstraintLayout;

        public homeworkHolder(@NonNull View itemView) {
            super(itemView);
            mConstraintLayout = itemView.findViewById(R.id.homework);
            title = itemView.findViewById(R.id.homework_title);
            ddl = itemView.findViewById(R.id.ddl);
            teacher = itemView.findViewById(R.id.teacher);
            details = itemView.findViewById(R.id.details);
        }
    }
}
