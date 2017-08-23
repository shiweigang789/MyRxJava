package com.duoduolicai360.myrxjava.module.rxjava2.operators.item;

import android.util.Log;

import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by swg on 2017/8/23.
 */

public class RxPublishSubjectActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxPublishSubjectActivit";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_PublishSubject);
    }

    @Override
    protected void doSomething() {
        mRxOperatorsText.append("PublishSubject\n");
        Log.e(TAG, "PublishSubject\n");
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxOperatorsText.append("First onSubscribe :"+d.isDisposed()+"\n");
                Log.e(TAG, "First onSubscribe :"+d.isDisposed()+"\n");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxOperatorsText.append("First onNext value :"+integer + "\n");
                Log.e(TAG, "First onNext value :"+integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxOperatorsText.append("First onError:"+e.getMessage()+"\n");
                Log.e(TAG, "First onError:"+e.getMessage()+"\n" );
            }

            @Override
            public void onComplete() {
                mRxOperatorsText.append("First onComplete!\n");
                Log.e(TAG, "First onComplete!\n");
            }
        });

        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);

        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxOperatorsText.append("Second onSubscribe :"+d.isDisposed()+"\n");
                Log.e(TAG, "Second onSubscribe :"+d.isDisposed()+"\n");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxOperatorsText.append("Second onNext value :"+integer + "\n");
                Log.e(TAG, "Second onNext value :"+integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxOperatorsText.append("Second onError:"+e.getMessage()+"\n");
                Log.e(TAG, "Second onError:"+e.getMessage()+"\n" );
            }

            @Override
            public void onComplete() {
                mRxOperatorsText.append("Second onComplete!\n");
                Log.e(TAG, "Second onComplete!\n");
            }
        });

        publishSubject.onNext(4);
        publishSubject.onNext(5);
        publishSubject.onComplete();

    }
}
