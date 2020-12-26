package com.blessy.com.bukaeakhale.ui.main.repository;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookRepository {

    @Query("SELECT book FROM book WHERE testament = 'OT'")
    List<String> findAllOldTestamentBooks();

    @Query("SELECT book FROM book WHERE testament = 'NT'")
    List<String> findAllNewTestamentBooks();

}
