package com.gewu.Notepad.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gewu.Notepad.R;
import com.gewu.Notepad.adapter.twoAdapter;

import java.util.Calendar;

public class twoFragment extends Fragment {


    private TextView years;
    private TextView mouth;
    private TextView news;
    private RecyclerView rec;
    private twoAdapter twoAdapter;
    private RelativeLayout tllibar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_two_fragment, container, false);
        initView(inflate);
        initRec();
        return inflate;
    }

    /**
     * 初始化Rec列表
     */
    private void initRec() {
        twoAdapter = new twoAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);
        rec.setAdapter(twoAdapter);
    }


    /**
     * findViewBy
     *
     * @param inflate
     */
    @SuppressLint("SetTextI18n")
    private void initView(final View inflate) {
        years = (TextView) inflate.findViewById(R.id.years);
        news = (TextView) inflate.findViewById(R.id.news);
        mouth = (TextView) inflate.findViewById(R.id.mouth);
        rec = (RecyclerView) inflate.findViewById(R.id.rec);


        //设置日期
        Calendar instance = Calendar.getInstance();
        years.setText(String.valueOf(instance.get(Calendar.YEAR)));
        int s = instance.get(Calendar.MONTH) + 1;
        if (s < 10) {
            mouth.setText("0" + s);
        } else {
            mouth.setText(s + "");
        }

        news.setOnClickListener(new View.OnClickListener() {
            private void initView(View inflate) {
                name = (EditText) inflate.findViewById(R.id.name);
                time1 = (EditText) inflate.findViewById(R.id.time1);
                endtime1 = (EditText) inflate.findViewById(R.id.endtime1);
                time2 = (EditText) inflate.findViewById(R.id.time2);
                endtime2 = (EditText) inflate.findViewById(R.id.endtime2);
                no = (Button) inflate.findViewById(R.id.no);
                ok = (Button) inflate.findViewById(R.id.ok);
            }

            private Button ok;
            private Button no;
            private EditText endtime2;
            private EditText time2;
            private EditText endtime1;
            private EditText time1;
            private EditText name;

            @Override
            public void onClick(View v) {
                View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.dlog, null, false);
                final PopupWindow popupWindow = new PopupWindow(inflate1, (int) ((getResources().getDisplayMetrics().widthPixels) - 200),
                        650);//参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                popupWindow.setFocusable(true);
                initView(inflate1);

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        twoAdapter.add();
                        twoAdapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, -100);

            }
        });
    }


}