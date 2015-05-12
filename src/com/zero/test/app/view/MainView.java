package com.zero.test.app.view;

import java.util.List;

/**
 * Created by 哲 on 2015/5/9.
 */
public interface MainView {
    void showProgressBar();

    void hideProgressBar();

    void showNoData();

    void showData(List<String> items);
}
