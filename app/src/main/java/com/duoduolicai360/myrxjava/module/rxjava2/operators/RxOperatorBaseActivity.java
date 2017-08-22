package com.duoduolicai360.myrxjava.module.rxjava2.operators;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.base.ToolbarBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by swg on 2017/8/22.
 */

public abstract class RxOperatorBaseActivity extends ToolbarBaseActivity{

    @BindView(R.id.rx_operators_btn)
    protected Button mRxOperatorsBtn;
    @BindView(R.id.rx_operators_text)
    protected TextView mRxOperatorsText;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_rx_operator_base;
    }

    @Override
    protected String getSubTitle() {
        return null;
    }

    protected abstract void doSomething();


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick(R.id.rx_operators_btn)
    public void onViewClicked() {
        mRxOperatorsText.append("\n");
        doSomething();
    }

}
