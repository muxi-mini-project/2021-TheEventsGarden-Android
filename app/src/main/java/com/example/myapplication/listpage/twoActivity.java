package com.example.myapplication.listpage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class twoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView name;
    private TextView startTime;
    private TextView endTime;
    private Button no;
    private Button ok;
    private ImageView action;
    private String timeExpend;
    private ProgressBarView progress;
    private int limitTime = 3;
    private Data data;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_time_activity);
        Window window = getWindow();
        View decorView = window.getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        data = getIntent().getParcelableExtra("data");
        initView();
        initToolbar();
    }

    /**
     * 初始化ToolBar并设置返回键
     */
    private void initToolbar() {
        toolbar.setTitle("新建待办");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    /**
     * findById
     */
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        name = (TextView) findViewById(R.id.name);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        no = (Button) findViewById(R.id.no);
        ok = (Button) findViewById(R.id.ok);
        action = (ImageView) findViewById(R.id.action);
        progress = (ProgressBarView) findViewById(R.id.progress);

        final TextView time1 = (TextView) findViewById(R.id.time1);
        final TextView time2 = (TextView) findViewById(R.id.time2);
        final TextView time4 = (TextView) findViewById(R.id.time4);
        final TextView time5 = (TextView) findViewById(R.id.time5);
        final TextView time7 = (TextView) findViewById(R.id.time7);
        final TextView time8 = (TextView) findViewById(R.id.time8);

        progress.setListener(new ProgressBarView.ProgressBarListener() {
            @Override
            public void time(long currentPlayTime) {
                startTime.setText("已进行".concat(formatTime(currentPlayTime)).concat("min"));
                SimpleDateFormat  sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                String hms = sdf.format(currentPlayTime - TimeZone.getDefault().getRawOffset());
                //时
                time1.setText(hms.substring(0, 1));
                time2.setText(hms.substring(1, 2));
                //分
                time4.setText(hms.substring(3, 4));
                time5.setText(hms.substring(4, 5));
                //秒
                time7.setText(hms.substring(6, 7));
                time8.setText(hms.substring(7, hms.length()));
            }

            @Override
            public void end(long currentPlayTime) {
                showDialog(time1.getText().toString() + time2.getText().toString(),
                        time4.getText().toString() + time5.getText().toString());
            }
        });

        final int time = getIntent().getIntExtra("time", 0);
        //startAnimation中需要的是毫秒值，getIntent传递过来的是分钟数

        endTime.setText("共" + formatTime(time * 1000 * 60) + "min");
        action.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                progress.startAnimation(time * 1000 * 60);
//                if (timeExpend == null) {
//                    Toast.makeText(twoActivity.this, "您还没有进行设置时间", Toast.LENGTH_SHORT).show();
//                } else {
//                    endTime.setText("共" + timeExpend + "min");
//                    Toast.makeText(twoActivity.this, timeExpend, Toast.LENGTH_SHORT).show();
//                }
            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setState(2);
                Intent intent = new Intent();
                intent.putExtra("data" , data);
                setResult(1,intent);
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.stopValueAnimator();
                showDialog(time1.getText().toString() + time2.getText().toString(),
                        time4.getText().toString() + time5.getText().toString());
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
            Intent intent = new Intent();
            intent.putExtra("data" , data);
            setResult(1,intent);
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void showDialog(String h, String min) {
        data.setState(3);
        View inflate1 = LayoutInflater.from(ok.getContext()).inflate(R.layout.dialog3, null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate1, (int) ((getResources().getDisplayMetrics().widthPixels) - 200),
                320);//参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        TextView time = inflate1.findViewById(R.id.time);
       TextView confirm = inflate1.findViewById(R.id.confirm);
       confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        setWidowColor(0);
    }

    private void setWidowColor(int i){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = i == 0 ? 0.7f : 1;
        getWindow().setAttributes(lp);
    }

    private String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return String.valueOf(minute);
    }

    /**
     * 重写Toolabe的返回键 监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 当前系统时间
     *
     * @return
     */
    public String indexTime() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String sim = dateFormat.format(date);
        return sim;
    }

    /**
     * 根据字符串换算时间
     *
     * @param strTime
     * @return
     */
    private long getTimeMillis(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return returnMillis;
    }

    /**
     * 计算总分钟数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private String getTimeExpend(String startTime, String endTime) {
        //传入字串类型 2016/06/28 08:30
        long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差
        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
        return String.valueOf(longHours * 60 + longMinutes);
    }
}