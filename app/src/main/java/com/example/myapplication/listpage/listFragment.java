package com.example.myapplication.listpage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.Calendar;

import com.example.myapplication.gardenpage.gardenActivity;


public class listFragment extends Fragment {

    private ImageView garden;//花园图标
    private ImageView calendar;//花园图标
    private TextView years;
    private TextView mouth;
    private TextView news;
    private TextView common;
    private TextView completes;
    private RecyclerView rec;
    private com.example.myapplication.listpage.twoAdapter twoAdapter;
    private RelativeLayout tllibar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_list, container, false);
        initView(inflate);
        initRec();
        return inflate;
    }

    /**
     * 初始化Rec列表
     */
    private void initRec() {
        twoAdapter = new twoAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linearLayoutManager);
        rec.setAdapter(twoAdapter);
        twoAdapter.setListener(new com.example.myapplication.listpage.twoAdapter.Listener() {
            @Override
            public void itemClick(String count) {
                completes.setText(count);
            }

            @Override
            public void startActivityForResult(Intent intent) {
                getActivity().startActivityForResult(intent,0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Data data1 = data.getParcelableExtra("data");
            if(data1 != null){
                twoAdapter.refreshStatus(data1);
            }
        }
    }

    /**
     * findViewBy
     *
     * @param inflate
     */
    @SuppressLint("SetTextI18n")
    private void initView(final View inflate) {
        years = (TextView) inflate.findViewById(R.id.years);
        news = (TextView) inflate.findViewById(R.id.count_down);
        mouth = (TextView) inflate.findViewById(R.id.mouth);
        rec = (RecyclerView) inflate.findViewById(R.id.rec);
        common = (TextView) inflate.findViewById(R.id.common);
        completes = (TextView) inflate.findViewById(R.id.complete);
        garden = inflate.findViewById(R.id.garden);

        garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), gardenActivity.class);
                startActivityForResult(intent, 41);
            }
        });

        calendar = inflate.findViewById(R.id.calendar);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivityForResult(intent, 42);
            }
        });


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
                View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.dlog, null, false);
                final PopupWindow popupWindow = new PopupWindow(inflate1, (int) ((getResources().getDisplayMetrics().widthPixels) - 200),
                        570);//参数为1.View 2.宽度 3.高度
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
                        if(TextUtils.isEmpty(name.getText()) ||
                                TextUtils.isEmpty(time1.getText()) ||
                                TextUtils.isEmpty(endtime1.getText()) ||
                                TextUtils.isEmpty(time2.getText()) ||
                                TextUtils.isEmpty(endtime2.getText())){
                            Toast.makeText(getActivity() , "请输入名称或者用时",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        twoAdapter.add(new Data(1,name.getText().toString() ,
                                Integer.parseInt(time1.getText().toString()),
                                Integer.parseInt(endtime1.getText().toString()),
                               Integer.parseInt(time2.getText().toString()),
                               Integer.parseInt(endtime2.getText().toString())
                        ));
                        twoAdapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, -100);
                backgroundAlpha(0.5f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //弹窗消失恢复原来的透明度
                        backgroundAlpha(1f);
                    }
                });

            }
        });

        //普通待办
        common.setOnClickListener(new View.OnClickListener() {
            private void initView(View inflate) {
                data = (EditText) inflate.findViewById(R.id.data);
                cancel = (TextView) inflate.findViewById(R.id.cancel);
                complete = (TextView) inflate.findViewById(R.id.complete);
            }

            private TextView complete;
            private TextView cancel;
            private EditText data;

            @Override
            public void onClick(View v) {
                View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.common_dialog, null, false);
                final PopupWindow popupWindow = new PopupWindow(inflate1, (int) ((getResources().getDisplayMetrics().widthPixels) - 150),
                        300);//参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                popupWindow.setFocusable(true);
                initView(inflate1);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                complete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        twoAdapter.add(new Data(0,data.getText().toString()));
                        twoAdapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, -100);
                backgroundAlpha(0.5f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //弹窗消失恢复原来的透明度
                        backgroundAlpha(1f);
                    }
                });

            }
        });
    }

    public void refreshStatus(Data data1) {
        if(twoAdapter != null){
            twoAdapter.refreshStatus(data1);
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }
}
