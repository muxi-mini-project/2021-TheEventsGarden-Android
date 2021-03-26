package com.example.myapplication.minepage;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Login.LoginActivity;
import com.example.myapplication.R;

public class mineFragment extends Fragment {
    private TextView name, modify, fund, month, rule;//昵称、修改信息、资金、月完成度、规则
    private ImageView head;//头像
    private Button exit,sure,cancel;//退出登录

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
                Intent intent;
                intent = new Intent(getActivity(), basicdataActivity.class);
                startActivityForResult(intent, 1);//跳转到修改界面，但没传数据！！《-------
            }
        });

        //查看月完成度
        month = (TextView) v.findViewById(R.id.tv_mine_month);//界面没出，还没跳转《--------
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), monthActivity.class);
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
        
        //退出登录
        exit = (Button) v.findViewById(R.id.bt_mine_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            private void initView(View inflate) {
               sure= (Button) inflate.findViewById(R.id.bt_sure_exit);
                cancel = (Button) inflate.findViewById(R.id.bt_cancel_exit);
            }
            @Override
            public void onClick(View v) {
                View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_log_out, null, false);
                final PopupWindow popupWindow = new PopupWindow(inflate1, (int) ((getResources().getDisplayMetrics().widthPixels) ),
                        400);//参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                popupWindow.setFocusable(true);
                initView(inflate1);

                 //确认退出
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });

                //取消退出
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                backgroundAlpha(0.5f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //弹窗消失恢复原来的透明度
                        backgroundAlpha(1f);
                    }
                });
                //背景变灰，点击旁边恢复？？？
                //从底部弹出
                popupWindow.showAtLocation(getView(), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
            }
        });
        return v;
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

