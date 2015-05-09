package com.zero.test.app.view;

import java.util.List;

/**
 * Created by ем on 2015/5/9.
 */
public interface MainView {
    public void showProgressBar();

    public void hideProgressBar();

    public void showNoData();

    public void showData(List<String> items);
}
