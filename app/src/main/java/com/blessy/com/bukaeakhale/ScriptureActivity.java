package com.blessy.com.bukaeakhale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blessy.com.bukaeakhale.ui.adapter.ChapterAdapter;
import com.blessy.com.bukaeakhale.ui.adapter.ScriptureAdapter;
import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;
import com.blessy.com.bukaeakhale.ui.main.service.BookService;
import com.blessy.com.bukaeakhale.ui.main.service.ScriptureService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static android.content.ContentValues.TAG;
import static com.blessy.com.bukaeakhale.MainActivity.BOOK;
import static com.blessy.com.bukaeakhale.MainActivity.CHAPTER;
import static com.blessy.com.bukaeakhale.MainActivity.SHARED_PREFS;

public class ScriptureActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView selectedBook;
    private TextView selectedChapter;

    private String book;
    private String chapter;

    private List<Scripture> scriptures;

    private RecyclerView scriptureRecyclerView;
    private ScriptureAdapter scriptureAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scripture);

        selectedBook = (TextView) findViewById(R.id.book);
        selectedChapter = (TextView) findViewById(R.id.chapter);

        selectedBook.setOnClickListener(this);
        scriptureRecyclerView = findViewById(R.id.scripturesRecyclerView);

        book = getIntent().getStringExtra(MainActivity.BOOK);
        chapter = getIntent().getStringExtra(MainActivity.CHAPTER);

        selectedBook.setText(book);
        selectedChapter.setText(chapter);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide app name

        CompletableFuture.supplyAsync(() -> ScriptureService.readScriptureByBookAndChapter(book, Integer.parseInt(chapter))
        ).thenAccept( s -> {
            scriptures = s;
            Log.i(TAG, "Chapters " + scriptures);
            scriptureAdapter = new ScriptureAdapter(scriptures);
            scriptureRecyclerView.setAdapter(scriptureAdapter);
        });
        saveCurrentScripture(book, chapter);

    }


    public void saveCurrentScripture(String book, String chapter){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BOOK, book);
        editor.putString(CHAPTER, chapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.book){
            Intent intent = new Intent(this, BookActivity.class);
            intent.putExtra(BOOK, book);
            intent.putExtra(CHAPTER, chapter + "");
            startActivity(intent);
        }
    }


    private void openActivity(Class newIntent){
        Intent intent = new Intent(this, newIntent);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.about){
            openActivity(AboutActivity.class);
        }
        if(id == R.id.home){
            openActivity(MainActivity.class);
        }
        if(id == R.id.search){
            openActivity(Search.class);
        }
        if(id == R.id.feedback){
            openActivity(FeedBack.class);
        }
        return super.onOptionsItemSelected(item);
    }

    float x1, x2, y1, y2;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                x2 = event.getY();
                if(x1 < x2){
                    Toast.makeText(this,"Swipe left", Toast.LENGTH_SHORT).show();
                }else if(x1 > x2){
                    Toast.makeText(this,"Swipe right", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}