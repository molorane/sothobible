package com.blessy.com.bukaeakhale.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blessy.com.bukaeakhale.R;

import java.util.List;

public class VerseAdapter extends RecyclerView.Adapter<VerseAdapter.VerseHolder> {

    private List<Integer> chapters;
    private OnVerseListener onVerseListener;

    public VerseAdapter(List<Integer> chapters, OnVerseListener onVerseListener) {
        this.chapters = chapters;
        this.onVerseListener = onVerseListener;
    }

    @NonNull
    @Override
    public VerseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid, parent, false);
        return new VerseHolder(view, onVerseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VerseHolder holder, int position) {
        holder.txtChapter.setText(String.valueOf(chapters.get(position)));
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public interface OnVerseListener{
        void onVerseClicked(int position);
    }

    public class VerseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtChapter;
        OnVerseListener onVerseListener;

        public VerseHolder(@NonNull View itemView, OnVerseListener onVerseListener) {
            super(itemView);
            txtChapter = (TextView) itemView.findViewById(R.id.txtChapter);
            itemView.setOnClickListener(this);
            this.onVerseListener = onVerseListener;
        }

        @Override
        public void onClick(View v) {
            onVerseListener.onVerseClicked(getAdapterPosition()+1);
        }
    }

}
