package com.blessy.com.bukaeakhale.ui.main.repository;

import androidx.room.Dao;
import androidx.room.Query;

import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;
import java.util.List;

@Dao
public interface ScriptureRepository {

    @Query("SELECT * FROM scripture WHERE id IN (:id)")
    List<Scripture> loadAllByIds(int id);



    @Query("SELECT * FROM scripture s " +
            "INNER JOIN book b ON s.book_id = b.id " +
            "WHERE b.book = :book AND s.chapter = :chapter")
    List<Scripture> readScriptureByBookAndChapter(String book, int chapter);

    @Query("SELECT count(s.verse) FROM scripture s " +
            "INNER JOIN book b ON s.book_id = b.id " +
            "WHERE b.book = :book AND s.chapter = :chapter")
    int getChapterVerses(String book, int chapter);

}
