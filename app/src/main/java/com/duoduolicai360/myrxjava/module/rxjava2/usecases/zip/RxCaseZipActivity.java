package com.duoduolicai360.myrxjava.module.rxjava2.usecases.zip;

import android.util.Log;

import com.duoduolicai360.myrxjava.model.CategoryResult;
import com.duoduolicai360.myrxjava.model.MobileAddress;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;
import com.duoduolicai360.myrxjava.net.Network;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by swg on 2017/8/23.
 */

public class RxCaseZipActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCaseZipActivity";

    @Override
    protected String getSubTitle() {
        return "zip 操作符使用场景";
    }

    @Override
    protected void doSomething() {
        Observable<MobileAddress> observable = Rx2AndroidNetworking.get("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                .build()
                .getObjectObservable(MobileAddress.class);

        Observable<CategoryResult> observable2 = Network.getGanApi()
                .getCategoryData("Android", 1, 1);

        Observable.zip(observable, observable2, new BiFunction<MobileAddress, CategoryResult, String>() {
            @Override
            public String apply(@NonNull MobileAddress mobileAddress, @NonNull CategoryResult categoryResult) throws Exception {
                return "合并后的数据为：手机归属地："+mobileAddress.getResult().getMobilearea()+"人名："+categoryResult.results.get(0).who;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "accept: 成功：" + s+"\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: 失败：" + throwable+"\n");
                    }
                });


    }
}
