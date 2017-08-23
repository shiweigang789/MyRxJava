package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by swg on 2017/8/23.
 */

public class RxMergeActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxMergeActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_merge);
    }

    @Override
    protected void doSomething() {
        Observable.merge(Observable.just(1,2),Observable.just(3,4),Observable.just(5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxOperatorsText.append("merge :" + integer + "\n");
                        Log.e(TAG, "accept: merge :" + integer + "\n" );
                    }
                });
    }
}
