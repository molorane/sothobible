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
import android.view.GestureDetector;
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

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static android.content.ContentValues.TAG;
import static com.blessy.com.bukaeakhale.MainActivity.BOOK;
import static com.blessy.com.bukaeakhale.MainActivity.CHAPTER;
import static com.blessy.com.bukaeakhale.MainActivity.SHARED_PREFS;

public class ScriptureActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener {

    private TextView selectedBook;
    private TextView selectedChapter;

    private String book;
    private String chapter;

    private List<Scripture> scriptures;
    public static final String PAGE = "fragment";

    private RecyclerView scriptureRecyclerView;
    private ScriptureAdapter scriptureAdapter;

    private GestureDetector gestureDetector;
    private static int MIN_DISTANCE = 150;
    private float x1, x2, y1, y2;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scripture);

        selectedBook = (TextView) findViewById(R.id.book);
        selectedChapter = (TextView) findViewById(R.id.chapter);

        selectedBook.setOnClickListener(this);
        selectedChapter.setOnClickListener(this);
        scriptureRecyclerView = findViewById(R.id.scripturesRecyclerView);

        book = getIntent().getStringExtra(MainActivity.BOOK);
        chapter = getIntent().getStringExtra(MainActivity.CHAPTER);
        saveCurrentScripture(book, chapter);

        selectedBook.setText(book);
        selectedChapter.setText(chapter);

        gestureDetector = new GestureDetector(scriptureRecyclerView.getContext(), this);

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
    }


    public void saveCurrentScripture(String book, String chapter){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BOOK, book);
        editor.putString(CHAPTER, chapter);
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.book){
            Intent intent = new Intent(this, BookActivity.class);
            intent.putExtra(BOOK, book);
            intent.putExtra(CHAPTER, chapter + "");
            intent.putExtra(PAGE, "fbook");
            startActivity(intent);
        }else if(v.getId() == R.id.chapter){
            Intent intent = new Intent(this, BookActivity.class);
            intent.putExtra(BOOK, book);
            intent.putExtra(CHAPTER, chapter + "");
            intent.putExtra(PAGE, "fchapter");
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


    private long startClickTime;
    static final int MAX_SWIPE_TIME = 200;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if (deltaX > MIN_DISTANCE)
                {
                    Toast.makeText(this,"swipeLeftToRight();", Toast.LENGTH_SHORT).show();
                }
                else if( Math.abs(deltaX) > MIN_DISTANCE)
                {
                    Toast.makeText(this,"swipeRightToLeft();", Toast.LENGTH_SHORT).show();
                }
                else if(deltaY > MIN_DISTANCE){
                    Toast.makeText(this,"swipeTopToBottom();", Toast.LENGTH_SHORT).show();
                }
                else if( Math.abs(deltaY) > MIN_DISTANCE){
                    Toast.makeText(this,"swipeBottopmToTop();", Toast.LENGTH_SHORT).show();
                }

                break;
        }

        Toast.makeText(this,"Touch event", Toast.LENGTH_SHORT).show();

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        Log.i("gestureDebug333", "flinged:" + e1 + "---" + e2);
        Log.i("gestureDebug333", "fling velocity:" + velocityX + "---" + velocityY);
        if (e1.getAction() == MotionEvent.ACTION_DOWN && e1.getX() > (e2.getX() + 300)){
            Toast.makeText(scriptureRecyclerView.getContext(), "flinged right to left", Toast.LENGTH_SHORT).show();
        }
        if (e1.getAction() == MotionEvent.ACTION_DOWN && e2.getX() > (e1.getX() + 300)){
            Toast.makeText(scriptureRecyclerView.getContext(), "flinged left to right", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}