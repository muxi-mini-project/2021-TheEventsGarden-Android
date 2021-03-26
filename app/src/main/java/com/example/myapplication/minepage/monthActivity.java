package com.example.myapplication.minepage;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class monthActivity extends AppCompatActivity {
    private ImageView left;//返回

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_month);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //实现返回的imageview《-------
        left = (ImageView) findViewById(R.id.iv_month_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Result(10);
                finish();//直接finish就能返回上一个gragment，即minefragment
            }
        });
    }
}

