package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by swg on 2017/9/4.
 */

public class RxCompositeDisposable extends RxOperatorBaseActivity {

    private static final String TAG = "RxCompositeDisposable";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_Flowable);
    }

    @Override
    protected void doSomething() {
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxOperatorsText.append("RxCompositeDisposable :"+integer+"\n");
                Log.e(TAG, "RxCompositeDisposable :"+integer+"\n" );
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxOperatorsText.append("RxCompositeDisposable :"+e.getMessage()+"\n");
            }

            @Override
            public void onComplete() {
                mRxOperatorsText.append("RxCompositeDisposable : onComplete\n");
                compositeDisposable.clear();
            }
        });
    }
}
