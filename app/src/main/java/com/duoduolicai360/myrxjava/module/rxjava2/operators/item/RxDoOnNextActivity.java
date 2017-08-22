package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by swg on 2017/8/22.
 */

public class RxDoOnNextActivity extends RxOperatorBaseActivity{

    private static final String TAG = "RxDoOnNextActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_doOnNext);
    }

    @Override
    protected void doSomething() {
        Observable.just(1,2,3,4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxOperatorsText.append("doOnNext 保存 " + integer + "成功" + "\n");
                        Log.e(TAG, "doOnNext 保存 " + integer + "成功" + "\n");
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                mRxOperatorsText.append("doOnNext :" + integer + "\n");
                Log.e(TAG, "doOnNext :" + integer + "\n");
            }
        });

    }
}
