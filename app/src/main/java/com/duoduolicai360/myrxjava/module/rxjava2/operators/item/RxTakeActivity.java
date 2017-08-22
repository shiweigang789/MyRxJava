package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/8/22.
 */

public class RxTakeActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxTakeActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_take);
    }

    @Override
    protected void doSomething() {
        Flowable.fromArray(1,2,3,4,5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxOperatorsText.append("take : "+integer + "\n");
                        Log.e(TAG, "accept: take : "+integer + "\n" );
                    }
                });
    }
}
