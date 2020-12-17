package com.blessy.com.bukaeakhale.ui.main;

import com.blessy.com.bukaeakhale.MainActivity;

import java.util.List;

public class TestamentBookService {

    private static List<String> otBooks;
    private static List<String> ntBooks;


    private TestamentBookService(){}

    public static List<String> getAllOldTestamentBooks(){

        if(otBooks == null){
            otBooks = MainActivity.appDatabase.scriptureRepository().findAllOldTestamentBooks();
        }

        return otBooks;
    }



    public static List<String> getAllNewTestamentBooks(){

        if(ntBooks == null){
            ntBooks = MainActivity.appDatabase.scriptureRepository().findAllNewTestamentBooks();
        }

        return ntBooks;
    }
}
