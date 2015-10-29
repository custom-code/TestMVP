package com.zero.test.base.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * 创建人: wangzhe
 * 创建日期: 2015/1/23
 * 时间: 16:42
 */
public abstract class BaseFragment extends Fragment {
    private OnFragmentDataChangeListener listener;

    /**
     * 回掉接口，启动fragment的activity实现此接口
     */
    public interface OnFragmentDataChangeListener {
        public void onDataChange(String tag, Intent intent);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnFragmentDataChangeListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    /**
     * 当fragment发生变化时通知Activity
     *
     * @param tag    fragment的标记
     * @param intent 用于传递参数
     */
    public void setDataChange(String tag, Intent intent) {
        listener.onDataChange(tag, intent);
    }


    /**
     * 初始化view
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);
}