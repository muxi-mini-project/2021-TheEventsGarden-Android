package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//视图和fragment的关联。修改CrimeListFragment类文件
public class mineFragment extends Fragment {
    private TextView name;//昵称
    private ImageView head;//头像
    private TextView modify;//点击文本，修改信息
    private TextView fund;//点击文本，查看资金
    private TextView month;//点击文本，查看月完成度
    private TextView rule;//点击文本，查看规则
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        name = (TextView) v.findViewById(R.id.tv_mine_name);//还没显示《--------

        head = (ImageView) v.findViewById(R.id.iv_mine_photo);//还没显示《-------

        //修改信息
        modify = (TextView) v.findViewById(R.id.tv_mine_modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), basicdataActivity.class);
                startActivity(intent);//跳转到修改界面，但没传数据！！《-------
            }
        });

        //查看月完成度
        month = (TextView) v.findViewById(R.id.tv_mine_month);//界面没出，还没跳转《--------
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), basicdataActivity.class);
                startActivity(intent);//跳转界面还没出图《------
            }
        });

        //查看规则
        rule= (TextView) v.findViewById(R.id.tv_mine_rule);//界面没出，还没跳转《--------
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), basicdataActivity.class);
                startActivity(intent);//跳转界面还没出图《-------
            }
        });
        return v;
    }

}

