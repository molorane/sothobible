package com.blessy.com.bukaeakhale.ui.frag.book;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blessy.com.bukaeakhale.BookActivity;
import com.blessy.com.bukaeakhale.R;
import com.blessy.com.bukaeakhale.ui.frag.book.communicator.Communicator;
import com.blessy.com.bukaeakhale.ui.frag.book.communicator.IAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class SectionsPagerAdapter extends FragmentPagerAdapter implements IAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_book, R.string.tab_chapter, R.string.tab_verse};
    private final Context mContext;
    private FragmentManager fragmentManager;
    public String book;
    private List<Communicator> fragments;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String book) {
        super(fm);
        mContext = context;
        fragmentManager = fm;
        this.book = book;
        fragments = new ArrayList<>();
        fragments.add(BookFragment.newInstance(book,"", this));
        fragments.add(ChapterFragment.newInstance(book,"", this));
        fragments.add(VerseFragment.newInstance(book,""));
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            ((BookActivity)(mContext)).changeToLibuka();
        }
        return (Fragment) fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragmentManager.beginTransaction().remove((Fragment) object).commitNowAllowingStateLoss();
    }

    @Override
    public void onSend(Object o, Fragment fragment) {

        for(Communicator c: fragments){
            if(fragment.equals(fragment)){
                c.onReceive(o);
            }
        }
    }
}