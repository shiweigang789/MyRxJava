package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by swg on 2017/8/22.
 */

public class RxLastActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxLastActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_last);
    }

    @Override
    protected void doSomething() {
        Observable.just(1,2,3)
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxOperatorsText.append("last : " + integer + "\n");
                        Log.e(TAG, "last : " + integer + "\n");
                    }
                });
    }
}
