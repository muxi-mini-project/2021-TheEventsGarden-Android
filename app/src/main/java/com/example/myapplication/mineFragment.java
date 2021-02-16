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

import java.util.List;


public class mineFragment extends Fragment {
    Info mInfo;
    InfoDatabase mInfoDatabase;
    InfoDao mInfoDao;
    private TextView name, modify, fund, month, rule;//昵称、修改信息、资金、月完成度、规则
    private ImageView head;//头像

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoDatabase = InfoDatabase.getInstance(getActivity());
        mInfoDao = mInfoDatabase.getInfoDao();

        mInfo = new Info("","","男");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);

        Info info = null;

        List<Info> list = mInfoDao.getAllInfo();
        if(list.size() != 0) {
            info = list.get(list.size() - 1);
        }

        name = (TextView) v.findViewById(R.id.tv_mine_name);
        if(list.size() == 0){
            name.setText("昵称");
        } else {
            name.setText(info.getName());
        }

        head = (ImageView) v.findViewById(R.id.iv_mine_photo);

        //修改信息
        modify = (TextView) v.findViewById(R.id.tv_mine_modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), basicdataActivity.class);

                mInfoDao.InsertInfo(mInfo);
                startActivityForResult(intent, 1);

                getActivity().finish();
            }
        });

        //查看月完成度
        month = (TextView) v.findViewById(R.id.tv_mine_month);//界面没出，还没跳转《--------
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), basicdataActivity.class);
                startActivity(intent);//跳转界面还没出图《------
            }
        });

        //查看规则
        rule = (TextView) v.findViewById(R.id.tv_mine_rule);//界面没出，还没跳转《--------
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), ruleActivity.class);
                startActivityForResult(intent, 3);//跳转到修改界面，但没传数据！！《-------
            }
        });
        return v;
    }

    //返回后刷新fragment
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //隐藏
        } else {
            Info info = null;
            List<Info> list = mInfoDao.getAllInfo();
            if(list.size() != 0) {
                info = list.get(list.size() - 1);
            }
            name.setText(info.getName());
        }
    }
}

