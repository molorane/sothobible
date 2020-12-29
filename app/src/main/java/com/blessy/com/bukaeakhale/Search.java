package com.blessy.com.bukaeakhale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.blessy.com.bukaeakhale.ui.main.service.BookService;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static android.content.ContentValues.TAG;

public class Search extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private Spinner spMatchMode, spLimitSearch, spFrom, spTo;
    private final String[] modeList = {"Match All Words", "Match Any Word", "Match Exact phrase"};
    private final String[] limitSearchList = {"Books of Moses", "Apocalyptic Books", "Epistles", "Gospels","Historical Books","Major Prophets","Minor Prophets","New Testament","Old Testament","Pauline Epistles", "Wisdom Books"};
    private String allBooks[];
    ArrayAdapter<String> adapterMatchMode, adapterLimitSearch, adapterFrom, adapterTo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        spMatchMode = (Spinner)findViewById(R.id.spMatchMode);
        spLimitSearch = (Spinner)findViewById(R.id.spLimitSearch);
        spFrom = (Spinner)findViewById(R.id.spFrom);
        spTo = (Spinner)findViewById(R.id.spTo);

        adapterMatchMode = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, modeList);
        adapterLimitSearch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, limitSearchList);

        adapterMatchMode.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        adapterLimitSearch.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spMatchMode.setAdapter(adapterMatchMode);
        spLimitSearch.setAdapter(adapterLimitSearch);


        CompletableFuture.supplyAsync(() -> BookService.getAllBooks()
        ).thenAccept( s -> {
            allBooks = s.toArray(new String[s.size()]);
            adapterFrom = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, allBooks);
            adapterTo = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, allBooks);
            spFrom.setAdapter(adapterFrom);
            spTo.setAdapter(adapterTo);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), modeList[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}