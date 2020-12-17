package com.blessy.com.bukaeakhale.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.blessy.com.bukaeakhale.ui.main.entity.Book;
import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;
import com.blessy.com.bukaeakhale.ui.main.repository.BookRepository;
import com.blessy.com.bukaeakhale.ui.main.repository.ScriptureRepository;

import static android.content.ContentValues.TAG;

@Database(entities = {Scripture.class, Book.class}, version = 1, exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {


    private static final String DATABASE_NAME = "bible_data";
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context){

        if(instance == null){
            synchronized (AppDatabase.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .createFromAsset("db/bible_data.db")
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract BookRepository bookRepository();

    public abstract ScriptureRepository scriptureRepository();
}
