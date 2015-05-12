package com.zero.test.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.zero.test.R;
import com.zero.test.base.view.BaseView;

/**
 * Created by 哲 on 2015/5/10.
 */
public abstract class BaseActivity extends FragmentActivity implements BaseView, View.OnClickListener {
    private Context context;
    private ProgressBar progressBar;
    private TextView title;

    protected abstract void setContentView(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void setViewClick(View view);

    protected abstract String setViewTitle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
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

    /**
     * 加载公共布局
     */
    private void initBaseView() {
        progressBar = (ProgressBar) findViewById(R.id.base_progress);
        title = (TextView) findViewById(R.id.base_title);
        title.setText(setViewTitle());
        initView();
    }

    /**
     * 显示弹出框
     *
     * @param str 弹出框字符串
     */
    protected void showToast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}