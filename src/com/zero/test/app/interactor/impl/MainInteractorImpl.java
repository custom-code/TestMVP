package com.zero.test.app.interactor.impl;

import android.os.Handler;
import com.zero.test.app.interactor.MainInteractor;
import com.zero.test.app.listener.OnLoadDataFinishedListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 哲 on 2015/5/9.
 */
public class MainInteractorImpl implements MainInteractor {
    @Override
    public void getData(final OnLoadDataFinishedListener onLoadDataFinishedListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadDataFinishedListener.onFinish(createArrayList());
            }
        }, 2000);
    }

    private List<String> createArrayList() {
        return Arrays.asList(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );
    }
}
