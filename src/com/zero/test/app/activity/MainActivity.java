package com.zero.test.app.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.zero.test.R;
import com.zero.test.app.presenter.MainPresenter;
import com.zero.test.app.presenter.impl.MainPresenterImpl;
import com.zero.test.app.view.MainView;

import java.util.List;

/**
 * Created by ем on 2015/5/9.
 */
public class MainActivity extends Activity implements MainView,AdapterView.OnItemClickListener {
    private Context context;
    private ListView mLvList;
    private ProgressBar mProgressBar;
    private MainPresenter mainPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.main_layout);
        mainPresenter = new MainPresenterImpl(this);
        initVIew();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResunme();
    }

    private void initVIew() {
        mLvList = (ListView) findViewById(R.id.main_listview);
        mProgressBar = (ProgressBar) findViewById(R.id.public_progress);
        mLvList.setOnItemClickListener(this);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showData(List<String> items) {
        mLvList.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        mainPresenter.onItemClick(position);
    }
}