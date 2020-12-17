package com.blessy.com.bukaeakhale.ui.main.service;

import com.blessy.com.bukaeakhale.MainActivity;

import java.util.List;

public class ScriptureService {

    private static List<Integer> chapters;

    private ScriptureService(){}

    public static List<Integer> getChaptersByBook(String book){
        if(chapters == null){
            chapters = MainActivity.appDatabase.scriptureRepository().findChaptersByBook(book);
        }
        return chapters;
    }

}
