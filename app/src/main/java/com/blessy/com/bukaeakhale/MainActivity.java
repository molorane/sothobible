package com.blessy.com.bukaeakhale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blessy.com.bukaeakhale.ui.main.AppDatabase;
import com.google.android.material.snackbar.Snackbar;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView btnAboutBible, btnReadBible, btnBook;
    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadBible = (CardView) findViewById(R.id.btnReadBible);
        btnAboutBible = (CardView) findViewById(R.id.btnAboutBible);

        btnReadBible.setOnClickListener(this);
        btnAboutBible.setOnClickListener(this);

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        Log.i(TAG, "Bible books " + appDatabase);
    }

    private void openActivity(Class newIntent){
        Intent intent = new Intent(this, newIntent);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnReadBible:
                openActivity(BookActivity.class); break;
            case R.id.btnAboutBible:
                openActivity(AboutActivity.class); break;
        }
    }
}