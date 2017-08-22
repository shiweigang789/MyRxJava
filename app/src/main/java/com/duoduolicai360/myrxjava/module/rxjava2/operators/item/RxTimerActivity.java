package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;
import com.duoduolicai360.myrxjava.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by swg on 2017/8/22.
 */

public class RxTimerActivity extends RxOperatorBaseActivity{

    private static final String TAG = "RxTimerActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_timer);
    }

    @Override
    protected void doSomething() {
        mRxOperatorsText.append("timer start : " + TimeUtil.getNowStrTime() + "\n");
        Log.e(TAG, "timer start : " + TimeUtil.getNowStrTime() + "\n");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mRxOperatorsText.append("timer :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                        Log.e(TAG, "timer :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                    }
                });
    }
}
