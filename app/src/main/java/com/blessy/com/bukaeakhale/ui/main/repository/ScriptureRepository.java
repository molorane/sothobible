package com.blessy.com.bukaeakhale.ui.main.repository;

import androidx.room.Dao;
import androidx.room.Query;

import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;
import java.util.List;

@Dao
public interface ScriptureRepository {

    @Query("SELECT * FROM scripture WHERE id IN (:id)")
    List<Scripture> loadAllByIds(int id);

    @Query("SELECT DISTINCT s.verse FROM scripture s " +
            "INNER JOIN book b ON s.book_id = b.id " +
            "WHERE b.book = :book AND s.chapter = :chapter")
    List<Integer> findAllChapterVersesByBookAndChapter(String book, int chapter);

    @Query("SELECT DISTINCT verse FROM scripture " +
            "WHERE book_id = :book_id AND chapter = :chapter")
    List<Integer> findAllChapterVersesByBookIdAndChapter(int book_id, int chapter);

    @Query("SELECT * FROM scripture s " +
            "INNER JOIN book b ON s.book_id = b.id " +
            "WHERE b.book = :book AND s.chapter = :chapter")
    List<Scripture> readScriptureByBookAndChapter(String book, int chapter);

    @Query("SELECT * FROM scripture " +
            "WHERE book_id = :bookId AND chapter = :chapter")
    List<Scripture> readScriptureByBookIdAndChapter(int bookId, int chapter);


    @Query("SELECT s.scripture FROM scripture s " +
            "INNER JOIN book b ON s.book_id = b.id " +
            "WHERE b.book = :book AND s.chapter = :chapter")
    List<String> readVersesByBookAndChapter(String book, int chapter);

    @Query("SELECT scripture FROM scripture " +
            "WHERE book_id = :book_id AND chapter = :chapter")
    List<String> readVersesByBookIdAndChapter(int book_id, int chapter);


    @Query("SELECT verse FROM scripture WHERE scripture LIKE :search")
    List<Integer> searchScripture(String search);

}
