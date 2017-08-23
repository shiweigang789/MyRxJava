package com.duoduolicai360.myrxjava.module.rxjava2.usecases.debounce;

import android.os.Bundle;
import android.util.Log;

import com.duoduolicai360.myrxjava.model.FoodList;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by swg on 2017/8/23.
 */

public class RxCaseDebounceActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCaseDebounceActivity";

    @Override
    protected String getSubTitle() {
        return "减少频繁的网络请求";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        RxView.clicks(mRxOperatorsBtn)
                .debounce(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        clickBtn();
                    }
                });

    }

    private void clickBtn() {
        Rx2AndroidNetworking.get("http://www.tngou.net/api/food/list")
                .addQueryParameter("rows", 2 + "")
                .build()
                .getObjectObservable(FoodList.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodList>() {
                    @Override
                    public void accept(@NonNull FoodList foodList) throws Exception {
                        Log.e(TAG, "accept: 获取数据成功:"+foodList.toString()+"\n" );
                        mRxOperatorsText.append("accept: 获取数据成功:"+foodList.toString()+"\n" );
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: 获取数据失败："+throwable.getMessage() +"\n");
                        mRxOperatorsText.append("accept: 获取数据失败："+throwable.getMessage() +"\n");
                    }
                });

    }

    @Override
    public void onViewClicked() {
        // 禁止响应点击操作
    }

    @Override
    protected void doSomething() {

    }
}
