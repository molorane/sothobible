package com.blessy.com.bukaeakhale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.blessy.com.bukaeakhale.ui.main.AppDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView btnAbout, btnRead, btnSettings, btnFeedBack, btnSearch;
    public static AppDatabase appDatabase;

    public static final String SHARED_PREFS = "SHARED_PREFS";
    public static final String BOOK = "com.blessy.com.bukaeakhale.book";
    public static final String CHAPTER = "com.blessy.com.bukaeakhale.chapter";

    private String book;
    private String chapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (CardView) findViewById(R.id.btnRead);
        btnAbout = (CardView) findViewById(R.id.btnAbout);
        btnSettings = (CardView) findViewById(R.id.btnSettings);
        btnFeedBack = (CardView) findViewById(R.id.btnFeedBack);
        btnSearch = (CardView) findViewById(R.id.btnSearch);



        btnRead.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnFeedBack.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        appDatabase = AppDatabase.getInstance(getApplicationContext());
    }

    private void openActivity(Class newIntent){
        Intent intent = new Intent(this, newIntent);
        startActivity(intent);
    }

    private void openScriptureActivity(Class newIntent){
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        Intent intent = new Intent(this, newIntent);
        book = loadScripture(BOOK);
        chapter = loadScripture(CHAPTER);
        intent.putExtra(BOOK, book.isEmpty()? "Genese" : book);
        intent.putExtra(CHAPTER, chapter.isEmpty()? "1" : chapter);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnRead:
                openScriptureActivity(ScriptureActivity.class); break;
            case R.id.btnAbout:
                openActivity(AboutActivity.class); break;
            case R.id.btnFeedBack:
                openActivity(FeedBack.class); break;
            case R.id.btnSearch:
                openActivity(Search.class); break;
        }
    }

    public String loadScripture(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}