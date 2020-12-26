package com.blessy.com.bukaeakhale.ui.main.service;

import com.blessy.com.bukaeakhale.MainActivity;

import java.util.List;

public class ScriptureService {

    private static int chapters;

    private ScriptureService(){}

    public static int countBookChapters(String book){
        if(chapters == 0){
            chapters = MainActivity.appDatabase.scriptureRepository().countBookChapters(book);
        }
        return chapters;
    }
}
