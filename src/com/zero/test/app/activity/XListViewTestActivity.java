package com.zero.test.app.activity;

import android.os.Bundle;
import android.view.View;
import com.zero.test.R;
import com.zero.test.base.activity.BaseActivity;
import com.zero.test.base.view.xlistview.XListView;

/**
 * Created by wangzhe on 2015/5/11.
 */
public class XListViewTestActivity extends BaseActivity {

    private XListView mXListView;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.xlistview_test_layout);
        initView();
    }

    @Override
    protected void initView() {
        mXListView = (XListView) findViewById(R.id.xlistview_test_xlistview);
    }

    @Override
    protected void setViewClick(View view) {

    }


    @Override
    protected String setViewTitle() {
        return null;
    }

    @Override
    public void showMessage() {

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