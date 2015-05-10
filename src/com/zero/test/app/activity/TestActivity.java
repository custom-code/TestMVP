package com.zero.test.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.zero.test.R;
import com.zero.test.app.presenter.impl.TestPresenterImpl;
import com.zero.test.base.activity.BaseActivity;
import com.zero.test.base.presenter.BasePresenter;
import com.zero.test.base.widget.CountdownView;

/**
 * Created by 哲 on 2015/5/10.
 */
public class TestActivity extends BaseActivity {
    private Context context;
    private Button mBtnSubmit;
    private TextView mTvText;
    private CountdownView mCountdownView;
    private BasePresenter basePresenter;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        context = TestActivity.this;
        setContentView(R.layout.test_layout);
        basePresenter = new TestPresenterImpl(this);
    }

    @Override
    protected void initView() {
        basePresenter.onViewCreate();
        mBtnSubmit = (Button) findViewById(R.id.test_button);
        mTvText = (TextView) findViewById(R.id.test_text);
        mCountdownView = (CountdownView) findViewById(R.id.test_countdown);
        mBtnSubmit.setOnClickListener(this);
        mCountdownView.setOnClickListener(this);
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
        Toast.makeText(context, "消息", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDataError() {

    }

    @Override
    public void setDataSuccess() {

    }

    @Override
    public void jumpToActivity() {

    }
}