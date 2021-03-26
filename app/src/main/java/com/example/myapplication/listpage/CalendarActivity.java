package com.example.myapplication.listpage;

import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class CalendarActivity extends AppCompatActivity {
    Time t = new Time();
    int ID = 0;
    int index = 0;

    TextView dayofmonth, percent, undone_text, done_text, c_common, c_count_down;
    private ImageView left;//返回

    protected void onCreate(Bundle savedInstanceState) {
        t.setToNow();
        ID = t.year * 10000 + (t.month + 1) * 100 + t.monthDay;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        initView();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void initView() {
        t.setToNow();
        dayofmonth = findViewById(R.id.dayofmonth);
        percent = findViewById(R.id.percent);
        undone_text = findViewById(R.id.undone_event);
        done_text = findViewById(R.id.done_event);
       // c_common = findViewById(R.id.c_common);
       // c_count_down = findViewById(R.id.c_count_down);

        dayofmonth.setText(String.valueOf(t.monthDay));
        left = (ImageView) findViewById(R.id.iv_calendar_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Result(10);
                finish();//直接finish就能返回上一个gragment，即minefragment
            }
        });


        CalendarView calendarView = findViewById(R.id.calendarView);
        //设置监视器，当摁下某日期时进行的事件
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = String.valueOf(dayOfMonth);
                /*
                当摁下某日期时，分别得到年月日，组合在一起形成日期ID
                */
                ID = (year * 10000 + (month + 1) * 100 + dayOfMonth);

                dayofmonth.setText(date);
            }
        });

      /*  c_common.setOnClickListener(new View.OnClickListener() {
            TextView complete, cancel;
            EditText events_name;

            @Override
            public void onClick(View v) {
                View inflate1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.common_dialog, null, false);
                final PopupWindow popupWindow = new PopupWindow(inflate1, (int) ((getResources().getDisplayMetrics().widthPixels) - 150),
                        300);//参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                popupWindow.setFocusable(true);

                complete = inflate1.findViewById(R.id.complete);
                cancel = inflate1.findViewById(R.id.cancel);
                events_name = inflate1.findViewById(R.id.name);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                complete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(getWindow().getDecorView().getRootView(), Gravity.CENTER, 0, -100);
            }
        });*/
    }
}