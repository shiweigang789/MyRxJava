package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.CompletableSubject;

/**
 * Created by swg on 2017/8/23.
 */

public class RxCompletableActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCompletableActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_Completable);
    }

    @Override
    protected void doSomething() {
        mRxOperatorsText.append("Completable\n");
        Log.e(TAG, "Completable\n");

        CompletableSubject.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mRxOperatorsText.append("onSubscribe : d :" + d.isDisposed() + "\n");
                        Log.e(TAG, "onSubscribe : d :" + d.isDisposed() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        mRxOperatorsText.append("onComplete\n");
                        Log.e(TAG, "onComplete\n");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mRxOperatorsText.append("onError :" + e.getMessage() + "\n");
                        Log.e(TAG, "onError :" + e.getMessage() + "\n");
                    }
                });


    }
}
