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

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.blessy.com.bukaeakhale.ui.frag.book.SectionsPagerAdapter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RequiresApi(api = Build.VERSION_CODES.N)
public class BookActivity extends AppCompatActivity implements Communicator {

    Switch onOffSwitch;
    TextView title;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    public static String book = "Genese";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), book);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        title = (TextView) findViewById(R.id.title);
        onOffSwitch = (Switch)  findViewById(R.id.swVerse);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Show verse tab = "+isChecked ,Toast.LENGTH_SHORT).show();
                //sectionsPagerAdapter.destroyItem();
            }

        });
    }

    @Override
    public void updateBook(String book) {
        this.book = book;
        sectionsPagerAdapter.book = book;
        title.setText(book);
    }

    public void changeToLibuka(){
        title.setText("Libuka");
    }

    @Override
    public void updateChapters(String book){
        FragmentManager fm = getSupportFragmentManager();
        ChapterFragment chapterFragment = (ChapterFragment) fm.findFragmentByTag("fragment_chapter");
        if(chapterFragment == null)
            Toast.makeText(getApplicationContext(), "Chapter fragment null " ,Toast.LENGTH_SHORT).show();
        else
            chapterFragment.setNewChapters(book);
    }

    @Override
    public void updateVerses(int capter) {
        FragmentManager fm = getSupportFragmentManager();
        VerseFragment verseFragment = (VerseFragment) fm.findFragmentByTag("fragment_verse");
    }
}