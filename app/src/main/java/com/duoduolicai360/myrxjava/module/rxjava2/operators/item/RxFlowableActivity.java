package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by swg on 2017/8/23.
 */

public class RxFlowableActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxFlowableActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_Flowable);
    }

    @Override
    protected void doSomething() {
        Flowable.just(1,2,3,4)
                .reduce(100, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                mRxOperatorsText.append("Flowable :"+integer+"\n");
                Log.e(TAG, "Flowable :"+integer+"\n" );
            }
        });
    }
}
