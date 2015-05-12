package com.zero.test.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.zero.test.R;
import com.zero.test.app.presenter.impl.TestPresenterImpl;
import com.zero.test.base.activity.BaseActivity;
import com.zero.test.base.listener.OnCountDownListener;
import com.zero.test.base.presenter.BasePresenter;
import com.zero.test.base.widget.CountDownView;
import com.zero.test.base.widget.PullToRefreshView;

/**
 * Created by 哲 on 2015/5/10.
 */
public class TestActivity extends BaseActivity implements OnCountDownListener, PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {
    private Context context;
    private Button mBtnSubmit;
    private TextView mTvText;
    private CountDownView mCountdownView;
    private PullToRefreshView mRefreshView;

    private BasePresenter basePresenter;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        context = TestActivity.this;
        setContentView(R.layout.test_layout);
        basePresenter = new TestPresenterImpl(this);
    }

    @Override
    protected void initView() {
        mBtnSubmit = (Button) findViewById(R.id.test_button);
        mTvText = (TextView) findViewById(R.id.test_text);
        mCountdownView = (CountDownView) findViewById(R.id.test_countdown);
        mRefreshView = (PullToRefreshView) findViewById(R.id.test_refresh_view);
        mBtnSubmit.setOnClickListener(this);
        mCountdownView.setOnClickListener(this);
        mCountdownView.setCountDownListener(this);
        mRefreshView.setOnFooterRefreshListener(this);
        mRefreshView.setOnHeaderRefreshListener(this);
    }

    @Override
    protected void setViewClick(View view) {
        switch (view.getId()) {
            case R.id.test_button:
                basePresenter.onStart();
                break;
            case R.id.test_countdown:
                mCountdownView.startCountDown();
                break;
        }
    }

    @Override
    protected String setViewTitle() {
        return "消息界面";
    }

    @Override
    public void showMessage() {
        showToast("消息");
    }

    @Override
    public void setDataError() {

    }

    @Override
    public void setDataSuccess() {

    }

    @Override
    public void jumpToActivity() {
        startActivity(new Intent(context, XListViewTestActivity.class));
    }

    @Override
    public void onFinished() {
        showToast("计时结束");
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mRefreshView.onFooterRefreshComplete();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 设置更新时间
//                mRefreshView.onHeaderRefreshComplete("最近更新:" + );
                mRefreshView.onHeaderRefreshComplete();
            }
        }, 200);
    }
}