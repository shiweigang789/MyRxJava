package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by swg on 2017/8/22.
 */

public class RxDebounceActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxDebounceActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_debounce);
    }

    @Override
    protected void doSomething() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxOperatorsText.append("debounce :" + integer + "\n");
                        Log.e(TAG,"debounce :" + integer + "\n");
                    }
                });
    }
}
