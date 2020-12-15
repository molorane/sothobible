package com.blessy.com.bukaeakhale.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blessy.com.bukaeakhale.R;

public class ChapterHolder extends RecyclerView.ViewHolder {

    public TextView txtChapter;

    public ChapterHolder(@NonNull View itemView) {
        super(itemView);
        txtChapter = (TextView) itemView.findViewById(R.id.txtChapter);
    }
}
