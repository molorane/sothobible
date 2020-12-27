package com.blessy.com.bukaeakhale.ui.frag.book;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blessy.com.bukaeakhale.BookActivity;
import com.blessy.com.bukaeakhale.R;
import com.blessy.com.bukaeakhale.ui.frag.book.communicator.Communicator;
import com.blessy.com.bukaeakhale.ui.main.service.BookService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment implements Communicator {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private ListView lvOT, lvNT;
    private ArrayAdapter<String> otAdapter, ntAdapter;
    private TextView title;
    private Communicator communicator;

    // TODO: Rename and change types of parameters
    private String bookParam;

    private String mParam2;
    private static List<String> otBooks;
    private static List<String> ntBooks;

    private BookActivity bookActivity;

    private ViewPager viewPager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public BookFragment() {
        // Required empty public constructor
    }

    public BookFragment(String book) {
        bookParam = book;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param book Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragment.
     */
    // TODO: Rename and change types and number of parameters
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static BookFragment newInstance(String book, String param2) {
        BookFragment fragment = new BookFragment(book);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bookActivity = (BookActivity) getActivity();
        viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);

        lvOT = getActivity().findViewById(R.id.lvOT);
        lvNT = getActivity().findViewById(R.id.lvNT);

        CompletableFuture.supplyAsync(() -> BookService.getAllOldTestamentBooks()
        ).thenAccept( s -> {
            otBooks = s;
            Log.i(TAG, "Bible OT books " + otBooks.toString());
            otAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, otBooks.toArray(new String[]{}));
            lvOT.setAdapter(otAdapter);
        });


        CompletableFuture.supplyAsync(() -> BookService.getAllNewTestamentBooks()
        ).thenAccept( s -> {
            ntBooks = s;
            ntAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, ntBooks.toArray(new String[]{}));
            lvNT.setAdapter(ntAdapter);
            Log.i(TAG, "Bible NT books " + ntBooks.toString());
        });


        lvOT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookParam = ((TextView)view).getText().toString();
                communicator.updateBook(bookParam);
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1); // Increment ViewPager's position
            }
        });

        lvNT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookParam = ((TextView)view).getText().toString();
                communicator.updateBook(bookParam);
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1); // Increment ViewPager's position
            }
        });

    }



    public void onItemClick(AdapterView<?> adv, View v, int arg2, long arg3) {
        // TODO Auto-generated method stub

        switch(adv.getId()) {
            case R.id.lvOT:
                Toast.makeText(getContext(), "list1", Toast.LENGTH_LONG).show();
                break;
            case R.id.lvNT:
                Toast.makeText(getContext(), "list2", Toast.LENGTH_LONG).show();
                break;
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void updateBook(String book) {

    }

    @Override
    public void onReceive(Object o) {

    }
}