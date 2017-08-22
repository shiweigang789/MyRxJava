package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by swg on 2017/8/22.
 */
public class RxMapActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxMapActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_map);
    }

    @Override
    protected void doSomething() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                mRxOperatorsText.append("accept : " + s +"\n");
                Log.e(TAG, "accept : " + s +"\n" );
            }
        });
    }
}
