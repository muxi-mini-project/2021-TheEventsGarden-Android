package listpage;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    Time t = new Time();

    EventsDao mEventsDao;
    EventsDatabase mEventsDatabase;

    TextView undone,undone_events,done_events,done,percent,date;
    ImageView mImageView;

    int ID = 0;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        t.setToNow();
        ID = t.year * 10000 + (t.month + 1) * 100 + t.monthDay;

        mEventsDatabase = EventsDatabase.getInstance(this);
        mEventsDao = mEventsDatabase.getEventsDao();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        undone = findViewById(R.id.undone);
        done = findViewById(R.id.done);
        undone_events = findViewById(R.id.undone_events);
        done_events = findViewById(R.id.done_events);
        percent = findViewById(R.id.percent);
        date = findViewById(R.id.date);
        mImageView = findViewById(R.id.imageView2);
        mImageView.setColorFilter(Color.rgb(235,252,245));

        updateView();

        CalendarView calendarView = findViewById(R.id.calendarView);
        //设置监视器，当摁下某日期时进行的事件
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date_text = String.valueOf(dayOfMonth);
                /*
                当摁下某日期时，分别得到年月日，组合在一起形成日期ID
                */
                index = 0;
                ID = (year * 10000 + (month + 1) * 100 + dayOfMonth);
                date.setText(date_text);
                updateView();
            }
        });
    }

    public void onClickInsert(View view){
        index++;
        CanBeAdded(false);
        updateView();
    }

    //刷新事件
    public void updateView() {
        //用于计数
        int count_sum = 0;
        int count_true = 0;
        //用于判断有无事件
        boolean DoneHasBeenAdded = false;
        boolean UnDoneHasBeenAdded = false;
        //建立一个events list用来读取所有events
        List<EventsLab> list = mEventsDao.getAllEvents();
        StringBuilder text = new StringBuilder();
        StringBuilder text_done = new StringBuilder();
        StringBuilder text_undone = new StringBuilder();
        StringBuilder text2 = new StringBuilder();
        text.append("无");

        //选择相应日期的事件
        for (int i = 0; i < list.size(); i++) {
            EventsLab eventsLab = list.get(i);
            for (int k = 0; k < list.size(); k++)
                if (eventsLab.getId() == ID * 100 + k + 1) {
                    count_sum++;
                    if (eventsLab.isCircumstance() == true) {
                        text_done.append(eventsLab.getTitle()).append(eventsLab.getId()-ID*100).append("\n");
                        DoneHasBeenAdded = true;
                        count_true++;
                    } else {
                        text_undone.append(eventsLab.getTitle()).append(eventsLab.getId() - ID * 100).append("\n");
                        UnDoneHasBeenAdded = true;
                    }
                }
            undone_events.setText(text_undone);
            done_events.setText(text_done);
        }

        if (DoneHasBeenAdded == false)
            done_events.setText(text);

        if (UnDoneHasBeenAdded == false)
            undone_events.setText(text);

        text2.append("完成度：").append(count_true).append("/").append(count_sum);
        percent.setText(text2);

        if (count_sum == count_true) {
            mImageView.setColorFilter(Color.rgb(181,242,217));
        } else {
            mImageView.setColorFilter(Color.rgb(230,195,191));
        }
    }

    //判断数据库中是否有相同的id，有则更换id再插入，无则直接插入
    public void CanBeAdded(boolean done) {
        EventsLab event;
        int record_number = -1;
        EventsLab eventsLab;
        List<EventsLab> list = mEventsDao.getAllEvents();

        for (int i = 0; i < list.size(); i++) {
            eventsLab = list.get(i);
            for (int k = 0; k < list.size(); k++)
                if (ID * 100 + k == eventsLab.getId()-1) {
                    record_number = i;
                }
        }

        if (record_number == -1) {
            event = new EventsLab("事件", ID * 100 + index, done);
            mEventsDao.InsertEvents(event);
        } else {
            eventsLab = list.get(record_number);
            int new_id = eventsLab.getId() + 1;
            System.out.println(new_id);
            event = new EventsLab("事件", new_id, done);
            mEventsDao.InsertEvents(event);
        }
    }
}
