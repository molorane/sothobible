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

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_book, R.string.tab_chapter, R.string.tab_verse};
    private final Context mContext;
    private FragmentManager fragmentManager;
    public String book;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String book) {
        super(fm);
        mContext = context;
        fragmentManager = fm;
        this.book = book;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                ((BookActivity)(mContext)).changeToLibuka();
                fragment = BookFragment.newInstance(book,""); break;
            case 1:
                fragment = ChapterFragment.newInstance(book,""); break;
            case 2:
                fragment = VerseFragment.newInstance(book,"1"); break;
        }
        return fragment;
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
}