package com.blessy.com.bukaeakhale.ui.main.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="scripture", foreignKeys = {
        @ForeignKey(
                entity = Book.class,
                parentColumns = "id",
                childColumns = "book_id"
        )})
public class Scripture implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public int id;

    @ColumnInfo(name = "book_id",index = true)
    public int bookId;

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

}
