package com.blessy.com.bukaeakhale.ui.main.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="book")
public class Book {
    @PrimaryKey
    @NonNull
    public int id;

    @NonNull
    public String testament;

    @NonNull
    public String book;
}
