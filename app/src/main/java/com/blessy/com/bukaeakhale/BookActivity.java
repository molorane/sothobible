package com.blessy.com.bukaeakhale;

import android.os.Build;
import android.os.Bundle;

import com.blessy.com.bukaeakhale.ui.frag.book.ChapterFragment;
import com.blessy.com.bukaeakhale.ui.frag.book.VerseFragment;
import com.blessy.com.bukaeakhale.ui.frag.book.communicator.Communicator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.blessy.com.bukaeakhale.ui.frag.book.SectionsPagerAdapter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static android.content.ContentValues.TAG;


@RequiresApi(api = Build.VERSION_CODES.N)
public class BookActivity extends AppCompatActivity {

    TextView title;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    public static String book = "Genese";
    public static String chapter = "1";
    private String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        book = getIntent().getStringExtra(MainActivity.BOOK);
        chapter = getIntent().getStringExtra(MainActivity.CHAPTER);
        page = getIntent().getStringExtra(ScriptureActivity.PAGE);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), book);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        title = (TextView) findViewById(R.id.title);

        if(page.equals("fchapter")){
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1); // Increment ViewPager's position
        }

    }

    public void updateBook(String book) {
        this.book = book;
        sectionsPagerAdapter.book = book;
        title.setText(book);
    }

    public void updateChapter(int chapter) {
        this.chapter = chapter+"";
    }

    public void changeToLibuka(){
        title.setText("Libuka");
    }

    public String getBook(){
        return book;
    }

    public String getChapter(){
        return chapter;
    }
}