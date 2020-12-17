package com.blessy.com.bukaeakhale.ui.main;

import com.blessy.com.bukaeakhale.MainActivity;

import java.util.List;

public class BookChapterService {

    private static List<Integer> chapters;


    private BookChapterService(){}

    public static List<Integer> getAllOldTestamentBooks(String book){

        if(chapters == null){
            chapters = MainActivity.appDatabase.scriptureRepository().findAllBookChapters(book);
        }
        return chapters;
    }


}
