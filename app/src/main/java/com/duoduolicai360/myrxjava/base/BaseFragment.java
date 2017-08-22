package com.duoduolicai360.myrxjava.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by swg on 2017/8/22.
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化
     */
    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0){
            return inflater.inflate(getContentViewLayoutID(),container,false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this,view);
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
