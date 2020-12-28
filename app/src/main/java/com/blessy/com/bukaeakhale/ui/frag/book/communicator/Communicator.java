package com.blessy.com.bukaeakhale.ui.frag.book.communicator;

public interface Communicator {
    default void onReceiveBook(Object o){}
    default void onReceiveChapter(Object o){}
}
