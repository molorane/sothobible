package com.blessy.com.bukaeakhale.ui.main.repository;

import androidx.room.Dao;
import androidx.room.Query;

import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;
import java.util.List;

@Dao
public interface ScriptureRepository {

    @Query("SELECT * FROM books WHERE id IN (:id)")
    List<Scripture> loadAllByIds(int id);

    @Query("SELECT DISTINCT book FROM books")
    List<String> findAllBooks();

    @Query("SELECT DISTINCT book FROM books WHERE testament = 'OT'")
    List<String> findAllOldTestamentBooks();

    @Query("SELECT DISTINCT book FROM books WHERE testament = 'NT'")
    List<String> findAllNewTestamentBooks();

    @Query("SELECT DISTINCT chapter FROM books WHERE book = :book")
    List<Integer> findAllBookChapters(String book);

    @Query("SELECT verse FROM books WHERE book = :book AND chapter = :chapter")
    List<Integer> findAllChapterVerses(String book, int chapter);

    @Query("SELECT * FROM books WHERE book = :book AND chapter = :chapter")
    List<Scripture> readChapter(String book, int chapter);

    @Query("SELECT scripture FROM books WHERE book = :book AND chapter = :chapter")
    List<String> readChapterVerses(String book, int chapter);

    @Query("SELECT verse FROM books WHERE book LIKE :search")
    List<Integer> searchScripture(String search);

}
