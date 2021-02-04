package listpage;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class twoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView name;
    private SeekBar seekBar;
    private TextView startTime;
    private TextView endTime;
    private Button no;
    private Button ok;
    private TimePicker mTimepicker;
    private ImageView action;
    private String timeExpend;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_time_activity);
        initView();
        initToolbar();
        initTimePick();
    }

    /**
     * 设置TimePick
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initTimePick() {
        mTimepicker = (TimePicker) findViewById(R.id.time);
        mTimepicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);  //设置点击事件不弹键盘
        mTimepicker.setIs24HourView(true);   //设置时间显示为24小时
        mTimepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {  //获取当前选择的时间
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                timeExpend = getTimeExpend(indexTime(), hourOfDay + ":" + minute);
            }
        });
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
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        no = (Button) findViewById(R.id.no);
        ok = (Button) findViewById(R.id.ok);
        mTimepicker = (TimePicker) findViewById(R.id.time);
        action = (ImageView) findViewById(R.id.action);

        action.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (timeExpend == null) {
                    Toast.makeText(twoActivity.this, "您还没有进行设置时间", Toast.LENGTH_SHORT).show();
                } else {
                    seekBar.setMax(Integer.parseInt(timeExpend));
                    endTime.setText("共" + timeExpend + "min");
                    Toast.makeText(twoActivity.this, timeExpend, Toast.LENGTH_SHORT).show();
                }
            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate1 = LayoutInflater.from(ok.getContext()).inflate(R.layout.dilog2, null, false);
                final PopupWindow popupWindow = new PopupWindow(inflate1, (int) ((getResources().getDisplayMetrics().widthPixels) - 200),
                        650);//参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                initView();
                popupWindow.showAtLocation(ok, Gravity.CENTER, 0, -100);
            }
        });

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