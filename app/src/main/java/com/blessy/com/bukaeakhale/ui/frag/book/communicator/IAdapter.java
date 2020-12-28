package com.blessy.com.bukaeakhale.ui.frag.book.communicator;

import androidx.fragment.app.Fragment;

public interface IAdapter {

    default void onSendBook(Object o, Fragment fragment){}
    default void onSendChapter(Object o, Fragment fragment){}
}
