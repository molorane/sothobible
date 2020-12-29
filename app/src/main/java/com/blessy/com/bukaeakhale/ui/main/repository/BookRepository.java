package com.blessy.com.bukaeakhale.ui.main.repository;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookRepository {

    @Query("SELECT book FROM book")
    List<String> findAllBooks();

    @Query("SELECT book FROM book WHERE testament = 'OT'")
    List<String> findAllOldTestamentBooks();

    @Query("SELECT book FROM book WHERE testament = 'NT'")
    List<String> findAllNewTestamentBooks();


    @Query("SELECT chapters FROM book b " +
            "WHERE b.book = :book")
    int getBookChapters(String book);

}
