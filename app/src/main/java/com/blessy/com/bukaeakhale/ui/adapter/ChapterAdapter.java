package com.blessy.com.bukaeakhale.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blessy.com.bukaeakhale.R;

import java.util.List;

public class ChapterAdapter  extends RecyclerView.Adapter<ChapterAdapter.ChapterHolder> {

    List<Integer> chapters;

    public ChapterAdapter(List<Integer> chapters) {
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ChapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid, parent, false);
        return new ChapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterHolder holder, int position) {
        holder.txtChapter.setText(String.valueOf(chapters.get(position)));
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public interface OnChapterListener{
        void onChapterClicked(int position);
    }

    public class ChapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtChapter;
        ChapterAdapter.OnChapterListener onChapterListener;

        public ChapterHolder(@NonNull View itemView) {
            super(itemView);
            txtChapter = (TextView) itemView.findViewById(R.id.txtChapter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
