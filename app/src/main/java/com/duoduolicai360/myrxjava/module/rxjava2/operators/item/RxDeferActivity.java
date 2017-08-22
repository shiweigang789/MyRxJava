package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by swg on 2017/8/22.
 */

public class RxDeferActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxDeferActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_defer);
    }

    @Override
    protected void doSomething() {
        Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1,2,3);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxOperatorsText.append("defer : " + integer + "\n");
                Log.e(TAG, "defer : " + integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxOperatorsText.append("defer : onError : " + e.getMessage() + "\n");
                Log.e(TAG, "defer : onError : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                mRxOperatorsText.append("defer : onComplete\n");
                Log.e(TAG, "defer : onComplete\n");
            }
        });
    }
}
