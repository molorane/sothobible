package com.blessy.com.bukaeakhale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Scripture extends AppCompatActivity implements View.OnClickListener {

    private TextView book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scripture);

        book = (TextView) findViewById(R.id.book);
        book.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.book){
            openActivity(BookActivity.class);
        }
    }


    private void openActivity(Class newIntent){
        Intent intent = new Intent(this, newIntent);
        startActivity(intent);
    }
}