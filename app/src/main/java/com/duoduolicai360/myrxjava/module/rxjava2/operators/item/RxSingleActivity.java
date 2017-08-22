package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import java.util.Random;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by swg on 2017/8/22.
 */

public class RxSingleActivity extends RxOperatorBaseActivity{

    private static final String TAG = "RxSingleActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_single);
    }

    @Override
    protected void doSomething() {
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        mRxOperatorsText.append("single : onSuccess : "+integer+"\n");
                        Log.e(TAG, "single : onSuccess : "+integer+"\n" );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mRxOperatorsText.append("single : onError : "+e.getMessage()+"\n");
                        Log.e(TAG, "single : onError : "+e.getMessage()+"\n");
                    }
                });
    }
}
