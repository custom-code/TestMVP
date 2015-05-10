package com.zero.test.base.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.zero.test.R;
import com.zero.test.base.view.BaseView;

/**
 * Created by �� on 2015/5/10.
 */
public abstract class BaseActivity extends FragmentActivity implements BaseView, View.OnClickListener {
    private ProgressBar progressBar;
    private TextView title;

    protected abstract void setContentView(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void setViewClick(View view);

    protected abstract String setViewTitle();


    /**
     * ���ع�������
     */
    private void initBaseView() {
        progressBar = (ProgressBar) findViewById(R.id.base_progress);
        title = (TextView) findViewById(R.id.base_title);
        title.setText(setViewTitle());
        initView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(savedInstanceState);
        initBaseView();
    }

    @Override
    public void onClick(View view) {
        setViewClick(view);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}