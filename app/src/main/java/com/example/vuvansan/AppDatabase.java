package com.example.vuvansan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {User.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private  static AppDatabase appdatabase;
    public abstract UserDao userDao();
    public static AppDatabase getAppDatabase(Context context){
        if(appdatabase==null){
            appdatabase= Room.databaseBuilder(context,
                    AppDatabase.class,"User.db").allowMainThreadQueries().build();

        }
        return  appdatabase;
    }
}
