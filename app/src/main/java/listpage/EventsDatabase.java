package listpage;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EventsLab.class},version = 1,exportSchema = false)
public abstract class EventsDatabase extends RoomDatabase {
    public static EventsDatabase INSTANCE;
    public static synchronized EventsDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),EventsDatabase.class,"Events")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract EventsDao getEventsDao();
}
