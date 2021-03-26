package com.example.myapplication.gardenpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class gardenActivity extends AppCompatActivity {
    private ImageView shop,bag,flower,pick;//商店，背包，花朵，采摘
    private ImageView left;//返回
    private ImageView notice;//花园规则提示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_garden);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        left = (ImageView) findViewById(R.id.iv_data_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Result(10);
                finish();//直接finish就能返回上一个gragment，即minefragment
            }
        });
        //花园规则弹窗《-----不完整，按钮没变亮，弹窗性状也没变
        notice = (ImageView) findViewById(R.id.iv_garden_notice);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog();//下方弹出dialog并且获取拍照和相册两个view
            }
        });

        shop = (ImageView) findViewById(R.id.iv_garden_shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gardenActivity.this, shopActivity.class);
                startActivityForResult(intent, 11);//跳转到修改界面，但没传数据！！《-------
            }
        });

        bag= (ImageView) findViewById(R.id.iv_garden_bag);
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gardenActivity.this, bagActivity.class);
                startActivityForResult(intent, 12);//跳转到修改界面，但没传数据！！《-------
            }
        });

    }
    private void showTypeDialog() {
        View inflate1 = View.inflate(gardenActivity.this, R.layout.dialog_garden_rule, null);
        final PopupWindow popupWindow = new PopupWindow(inflate1, 900, 600);//参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(inflate1, Gravity.CENTER, 0, -100);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //弹窗消失恢复原来的透明度
                backgroundAlpha(1f);
            }
        });

    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

}
