package com.duoduolicai360.myrxjava.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.duoduolicai360.myrxjava.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;

/**
 * 封装一个带ToolBar和Butterknife注解的Activity基类
 * Created by swg on 2017/8/22.
 */

public abstract class ToolbarBaseActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title_text)
    TextView mTitleName;

    /**
     * 设置标题文本
     */
    protected abstract String getSubTitle();

    @SuppressLint("deprecated")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.blue));
        if (getContentViewLayoutID() != 0){
            initToolbar();
        }
    }

    @SuppressLint("NewApi")
    protected void initToolbar(){
     if (mToolbar != null){
         mToolbar.setTitle("");
         mTitleName.setText(getSubTitle());
         if (isShowBack()){
             showBack();
         }
     }
    }

    @SuppressLint("NewApi")
    private void showBack() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);

    }

    protected boolean isShowBack() {
        return true;
    }
}
