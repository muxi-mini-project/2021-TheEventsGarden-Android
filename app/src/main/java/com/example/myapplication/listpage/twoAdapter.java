package com.example.myapplication.listpage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;


import java.util.ArrayList;
import java.util.List;

public class twoAdapter extends RecyclerView.Adapter<twoAdapter.Items> {

    //全部待办数据
    private List<Data> dataList = new ArrayList<>();
    private Context mContext;
    int i = 0;
    //数据改变后要触发的接口
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void refreshStatus(Data data1) {
        for(Data data : dataList){
            if(data.equals(data1)){
                data.setState(data1.getState());
                break;
            }
        }
        stopSize();
        notifyDataSetChanged();
    }

    public interface Listener {
        void itemClick(String count);

        void startActivityForResult(Intent intent);
    }

    public twoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //添加待办数据
    public void add(Data d) {
        this.i++;
        dataList.add(d);
        //返回数量
        listener.itemClick(stopSize());
    }

    //将已完成的数据放到最后面
    public String stopSize() {
        List<Data> stopList = new ArrayList<>();
        if (dataList != null && dataList.size() != 0) {
            //遍历全部数据
            for (int i = dataList.size() - 1; i >= 0; i--) {
                //判断状态是否为已完成
                if (dataList.get(i).getState() == 3) {
                    stopList.add(dataList.get(i));
                    dataList.remove(i);
                }
            }
            dataList.addAll(stopList);
        }
        //返回完成数与总数
        return "今日已完成(" + String.valueOf(stopList.size()) + "/" + String.valueOf(dataList.size()) + ")";
    }

    @NonNull
    @Override
    public twoAdapter.Items onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_item, parent, false);
        return new Items(inflate);
    }

    private void getIntent(Data data){
        Intent intent = new Intent(mContext, twoActivity.class);
        int second = data.getEndSecond() - data.getStartSecond();
        int time = second + (data.getEndMinute() - data.getStartMinute()) * 60;
        intent.putExtra("time" , time);
        intent.putExtra("data" , data);
        listener.startActivityForResult(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull final twoAdapter.Items holder, final int position) {
        final Data data = dataList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getType() == 1 && data.getState() != 3){
                    getIntent(data);
                }

            }
        });
        //设置空背景（无蒙版）
        holder.relative.setBackground(null);
        //设置隐藏 已取消
        holder.cancelled.setVisibility(View.GONE);
        holder.action.setTextColor(Color.WHITE);
        holder.action.setVisibility(View.VISIBLE);
        holder.withelled.setTextColor(0xff6B6B6B);
        //判断类型是不是新建状态
        if (data.getState() == 0) {
            //如果类型是1（计时待办）
            if (data.getType() == 1) {
                holder.action.setText(mContext.getResources().getString(R.string.two_start));
            } else {
                holder.action.setText(mContext.getResources().getString(R.string.two_complete));
            }
        }//适用于计时待办已经点击开始时
        else if (data.getState() == 1) {
            holder.action.setText(mContext.getResources().getString(R.string.two_cancel));
        }//适用于计时待办已经点击暂停时
        else if (data.getState() == 2) {
            holder.relative.setBackgroundResource(R.drawable.two_item_cancel);
            holder.cancelled.setVisibility(View.VISIBLE);
            holder.action.setText(mContext.getResources().getString(R.string.two_continue));
            holder.action.setTextColor(0xff9C6064);
            holder.withelled.setTextColor(0xff9C6064);
        }//适用于所有待办停止时
        else if (data.getState() == 3) {
            holder.relative.setBackgroundResource(R.drawable.two_item_stop);
            holder.action.setVisibility(View.GONE);
        }
        //开始的点击事件
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果类型是1（计时待办）
                if (dataList.get(position).getType() == 1) {
                    //若点击前状态是新建状态
                    if (data.getState() == 0) {
                        getIntent(data);
                        //设置现在状态为开始时的状态
                        data.setState(1);
                    } //若点击前状态是开始时
//                    else if (data.getState() == 1) {
//                        //设置现在状态为暂停时的状态
//                        data.setState(2);
//                    } //若点击前状态是暂停时
                    else if (data.getState() == 2) {
                        //设置现在状态为开始时的状态
                        getIntent(data);
                    }
//
                } else
                if(dataList.get(position).getType() == 0) {
                    //设置现在状态为完成时的状态
                    data.setState(3);
                }
                //返回完成数与总数
                listener.itemClick(stopSize());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Items extends RecyclerView.ViewHolder {
        private Button action;
        private TextView cancelled;
        private TextView withelled;
        private RelativeLayout relative;

        public Items(View inflate) {
            super(inflate);
            action = (Button) inflate.findViewById(R.id.action);
            cancelled = (TextView) inflate.findViewById(R.id.cancelled);
            withelled = (TextView) inflate.findViewById(R.id.withelled);
            relative = (RelativeLayout) inflate.findViewById(R.id.relative);
        }
    }
}
