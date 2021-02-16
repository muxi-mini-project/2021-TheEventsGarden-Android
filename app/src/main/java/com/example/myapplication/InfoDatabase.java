package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Info.class}, version = 1, exportSchema = false)
public abstract class InfoDatabase extends RoomDatabase {
    private static InfoDatabase INSTANCE = null;

    public static synchronized InfoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), InfoDatabase.class, "InfoDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract InfoDao getInfoDao();
}
