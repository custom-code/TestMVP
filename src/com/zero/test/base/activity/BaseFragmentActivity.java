package com.zero.test.base.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zero.test.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 创建人: wangzhe
 * 创建日期: 2015/1/23
 * 时间: 16:33
 */
public abstract class BaseFragmentActivity extends BaseActivity {
    private List<BaseFragment> fragments;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
    }

    private void beginTransaction() {
        transaction = manager.beginTransaction();
    }

    private void commitTransaction() {
        transaction.commit();
    }


    public void addFragment(int resouceId, BaseFragment fragment, String tag) {
        beginTransaction();
        if (fragments == null) {
            fragments = new ArrayList<BaseFragment>();
            transaction.add(resouceId, fragment, tag).commit();
            fragments.add(fragment);
        } else {

        }

    }

    public BaseFragment getFragmentByTag(String tag) {
        if (fragments == null) {
            return null;
        } else {
            for (BaseFragment fragment : fragments) {
                if (tag.equals(fragment.getTag())) {
                    return fragment;
                }
            }
        }
        return null;
    }

}