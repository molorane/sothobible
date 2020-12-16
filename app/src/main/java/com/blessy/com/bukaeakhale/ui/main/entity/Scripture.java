package com.blessy.com.bukaeakhale.ui.main.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="books")
public class Scripture implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public Integer id;

    @ColumnInfo(name = "testament")
    @NonNull
    public String testament;

    @ColumnInfo(name = "book")
    @NonNull
    public String book;

    @ColumnInfo(name = "chapter")
    @NonNull
    public int chapter;

    @ColumnInfo(name = "verse")
    @NonNull
    public int verse;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "scripture")
    @NonNull
    public String scripture;


    public Scripture(){}

    public Scripture(String book, int chapter, int verse) {
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
    }
}
