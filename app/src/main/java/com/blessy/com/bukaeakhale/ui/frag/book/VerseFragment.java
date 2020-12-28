package com.blessy.com.bukaeakhale.ui.frag.book;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blessy.com.bukaeakhale.BookActivity;
import com.blessy.com.bukaeakhale.R;
import com.blessy.com.bukaeakhale.ui.adapter.ChapterAdapter;
import com.blessy.com.bukaeakhale.ui.adapter.VerseAdapter;
import com.blessy.com.bukaeakhale.ui.frag.book.communicator.Communicator;
import com.blessy.com.bukaeakhale.ui.main.AppUtils;
import com.blessy.com.bukaeakhale.ui.main.service.BookService;
import com.blessy.com.bukaeakhale.ui.main.service.ScriptureService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;
import static com.blessy.com.bukaeakhale.ui.main.AppUtils.generateList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


@RequiresApi(api = Build.VERSION_CODES.N)
public class VerseFragment extends Fragment implements Communicator, VerseAdapter.OnVerseListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String book;
    private int chapter;

    private List<Integer> verses;
    private BookActivity bookActivity;

    private RecyclerView versesRecyclerView;
    private VerseAdapter versesAdapter;

    public VerseFragment(String book, int chapter) {
        this.book = book;
        this.chapter = chapter;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param book Parameter 1.
     * @param chapter Parameter 2.
     * @return A new instance of fragment VerseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VerseFragment newInstance(String book, int chapter) {
        VerseFragment fragment = new VerseFragment(book, chapter);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bookActivity = (BookActivity) getActivity();
        versesRecyclerView = getActivity().findViewById(R.id.versesRecyclerView);

        CompletableFuture.supplyAsync(() -> ScriptureService.getChapterVerses(book, chapter)
        ).thenAccept( s -> {
            verses = generateList(s);
            Log.i(TAG, "Verses " + verses);
            versesAdapter = new VerseAdapter(verses, this);
            versesRecyclerView.setAdapter(versesAdapter);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verse, container, false);
    }

    public void setNewChapter(String book){
        this.book = book;
        verses.clear();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                verses.addAll(generateList(ScriptureService.getChapterVerses(book, chapter)));
                Log.i(TAG, "Chapters change " + verses);
                versesAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setBook(String book){
        this.book = book;
    }

    public void setChapter(int chapter){
        this.chapter = chapter;
    }

    @Override
    public void onReceiveChapter(Object o) {
        setNewChapter((String)o);
    }

    @Override
    public void onVerseClicked(int position) {
        Toast.makeText(getActivity(), "Feature under development",Toast.LENGTH_SHORT).show();
    }
}