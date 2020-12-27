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

import com.blessy.com.bukaeakhale.R;
import com.blessy.com.bukaeakhale.ui.adapter.ChapterAdapter;
import com.blessy.com.bukaeakhale.ui.frag.book.communicator.Communicator;
import com.blessy.com.bukaeakhale.ui.frag.book.communicator.IAdapter;
import com.blessy.com.bukaeakhale.ui.main.service.BookService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChapterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class ChapterFragment extends Fragment implements Communicator, ChapterAdapter.OnChapterListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String book;
    private String mParam2;
    private List<Integer> chapters;
    private Communicator communicator;

    private RecyclerView chaptersRecyclerView;
    private ChapterAdapter chaptersAdapter;
    private IAdapter iAdapter;

    public ChapterFragment() {
        // Required empty public constructor
    }

    public ChapterFragment(String book, IAdapter iAdapter) {
        this.book = book;
        this.iAdapter = iAdapter;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param book Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChapterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChapterFragment newInstance(String book, String param2, IAdapter iAdapter) {
        ChapterFragment fragment = new ChapterFragment(book, iAdapter);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, book);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chaptersRecyclerView = getActivity().findViewById(R.id.chaptersRecyclerView);

        CompletableFuture.supplyAsync(() -> BookService.getBookChapters(book)
        ).thenAccept( s -> {
            chapters = generateList(s);
            Log.i(TAG, "Chapters " + chapters);
            chaptersAdapter = new ChapterAdapter(chapters, this);
            chaptersRecyclerView.setAdapter(chaptersAdapter);
        });

    }

    public List<Integer> generateList(int chapters){
        return IntStream.rangeClosed(1, chapters).mapToObj(Integer::valueOf).collect(Collectors.toList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }


    public void setNewChapters(String book){
        Toast.makeText(getContext(), "New book "+book, Toast.LENGTH_LONG).show();
        chapters.clear();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                chapters.addAll(generateList(BookService.getBookChapters(book)));
                Log.i(TAG, "Chapters change " + chapters);
                chaptersAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onReceive(Object o) {
        setNewChapters((String)o);
    }

    @Override
    public void onChapterClicked(int position) {
        Toast.makeText(getActivity(), "chapter clicked "+position ,Toast.LENGTH_SHORT).show();
    }
}