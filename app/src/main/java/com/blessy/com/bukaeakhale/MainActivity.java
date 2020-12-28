package com.blessy.com.bukaeakhale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.blessy.com.bukaeakhale.ui.main.AppDatabase;
import com.blessy.com.bukaeakhale.ui.main.service.BookService;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.CompletableFuture;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView btnAbout, btnRead, btnSettings;
    public static AppDatabase appDatabase;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (CardView) findViewById(R.id.btnRead);
        btnAbout = (CardView) findViewById(R.id.btnAbout);
        btnSettings = (CardView) findViewById(R.id.btnSettings);

        btnRead.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnSettings.setOnClickListener(this);

        appDatabase = AppDatabase.getInstance(getApplicationContext());
    }

    private void openActivity(Class newIntent){
        Intent intent = new Intent(this, newIntent);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnRead:
                openActivity(Scripture.class); break;
            case R.id.btnAbout:
                openActivity(AboutActivity.class); break;
        }
    }
}