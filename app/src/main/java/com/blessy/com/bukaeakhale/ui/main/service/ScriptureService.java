package com.blessy.com.bukaeakhale.ui.main.service;

import com.blessy.com.bukaeakhale.MainActivity;
import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;

import java.util.List;

public class ScriptureService {

    private ScriptureService(){}

    public static List<Scripture> readScriptureByBookAndChapter(String book, int chapter){
        return MainActivity.appDatabase.scriptureRepository().readScriptureByBookAndChapter(book, chapter);
    }

}
