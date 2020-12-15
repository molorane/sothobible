package com.blessy.com.bukaeakhale.ui.main;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;
import com.blessy.com.bukaeakhale.ui.main.repository.ScriptureRepository;

@Database(entities = {Scripture.class}, version = 1, exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {


    private static final String DATABASE_NAME = "bible_data";
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context){

        if(instance == null){
            synchronized (AppDatabase.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .createFromAsset("database/bible_data.db")
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract ScriptureRepository scriptureRepository();
}