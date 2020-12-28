package com.blessy.com.bukaeakhale.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.blessy.com.bukaeakhale.R;
import com.blessy.com.bukaeakhale.ScriptureActivity;
import com.blessy.com.bukaeakhale.ui.main.entity.Scripture;

import java.util.List;

public class ScriptureAdapter extends RecyclerView.Adapter<ScriptureAdapter.ScriptureHolder> {

    private List<Scripture> chapters;

    public ScriptureAdapter(List<Scripture> chapters) {
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ScriptureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scripture_row, parent, false);
        return new ScriptureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScriptureHolder holder, int position) {
        holder.txtVerse.setText(String.valueOf(chapters.get(position).verse));
        holder.txtScripture.setText(String.valueOf(chapters.get(position).scripture));
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public interface OnChapterListener{
        void onChapterClicked(int position);
    }

    public class ScriptureHolder extends RecyclerView.ViewHolder {

        public TextView txtVerse;
        public TextView txtScripture;


        public ScriptureHolder(@NonNull View itemView) {
            super(itemView);
            txtVerse = (TextView) itemView.findViewById(R.id.txtVerse);
            txtScripture = (TextView) itemView.findViewById(R.id.txtScripture);
        }

    }

}
