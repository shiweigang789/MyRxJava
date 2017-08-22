package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;
import com.duoduolicai360.myrxjava.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by swg on 2017/8/22.
 */

public class RxIntervalActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxIntervalActivity";
    private Disposable mDisposable;

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_interval);
    }

    @Override
    protected void doSomething() {
        mRxOperatorsText.append("interval start : " + TimeUtil.getNowStrTime() + "\n");
        Log.e(TAG, "interval start : " + TimeUtil.getNowStrTime() + "\n");
        mDisposable = Observable.interval(3,2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mRxOperatorsText.append("interval :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                        Log.e(TAG, "interval :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}
