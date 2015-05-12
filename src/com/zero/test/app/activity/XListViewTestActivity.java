package com.zero.test.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import com.zero.test.R;
import com.zero.test.base.activity.BaseActivity;
import com.zero.test.base.widget.xlistview.XListView;

import java.util.ArrayList;

/**
 * Created by wangzhe on 2015/5/11.
 */
public class XListViewTestActivity extends BaseActivity implements XListView.IXListViewListener {
    private Context context;
    private Handler mHandler;
    private XListView mXListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<String>();
    private int start = 0;
    private static int refreshCnt = 0;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.xlistview_test_layout);
        context = XListViewTestActivity.this;
        initView();
    }

    @Override
    protected void initView() {
        mXListView = (XListView) findViewById(R.id.xlistview_test_xlistview);
        mXListView.setHeaderDividersEnabled(false);
        mXListView.setFooterDividersEnabled(false);
        mXListView.setPullLoadEnable(true);
        mXListView.setPullRefreshEnable(true);
        mXListView.setXListViewListener(this);
        mAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);//用模拟的数据填充系统的adapter
        mXListView.setAdapter(mAdapter);//指定adapter
        mHandler = new Handler();
    }

    private void geneItems() {
        for (int i = 0; i != 5; ++i) {
            items.add("refresh cnt" + (++start));
        }
    }

    private void onLoad() {
        mXListView.stopRefresh();
        mXListView.stopLoadMore();
        mXListView.setRefreshTime("刚刚");
    }

    @Override
    protected void setViewClick(View view) {

    }


    @Override
    protected String setViewTitle() {
        return "下拉刷新的ListView";
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

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++refreshCnt;
                items.clear();
                geneItems();
                // mAdapter.notifyDataSetChanged();
                mAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
                mXListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }
}