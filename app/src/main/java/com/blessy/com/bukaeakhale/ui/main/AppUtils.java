package com.blessy.com.bukaeakhale.ui.main;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class AppUtils {

    private AppUtils(){}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Integer> generateList(int chapters){
        return IntStream.rangeClosed(1, chapters).mapToObj(Integer::valueOf).collect(Collectors.toList());
    }
}
