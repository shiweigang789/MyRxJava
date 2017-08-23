package com.duoduolicai360.myrxjava.module.rxjava2.usecases.interval;

import android.util.Log;

import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by swg on 2017/8/23.
 */

public class RxCaseIntervalActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCaseIntervalActivity";

    private Disposable mDisposable;

    @Override
    protected String getSubTitle() {
        return "间隔任务实现心跳";
    }

    @Override
    protected void doSomething() {
        mDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e(TAG, "accept: doOnNext : "+aLong );
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e(TAG, "accept: 设置文本 ："+aLong );
                        mRxOperatorsText.append("accept: 设置文本 ："+aLong +"\n");
                    }
                });
    }

    /**
     * 销毁时停止心跳
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null){
            mDisposable.dispose();
        }
    }

}
