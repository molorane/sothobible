package com.blessy.com.bukaeakhale.ui.main.service;

import com.blessy.com.bukaeakhale.MainActivity;

import java.util.List;

public class BookService {

    private static List<String> otBooks;
    private static List<String> ntBooks;

    private BookService(){}

    public static List<String> getAllOldTestamentBooks(){
        if(otBooks == null){
            otBooks = MainActivity.appDatabase.bookRepository().findAllOldTestamentBooks();
        }
        return otBooks;
    }

    public static List<String> getAllNewTestamentBooks(){
        if(ntBooks == null){
            ntBooks = MainActivity.appDatabase.bookRepository().findAllNewTestamentBooks();
        }
        return ntBooks;
    }
}
