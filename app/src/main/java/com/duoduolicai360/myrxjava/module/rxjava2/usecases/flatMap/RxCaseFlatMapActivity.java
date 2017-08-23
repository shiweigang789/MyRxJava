package com.duoduolicai360.myrxjava.module.rxjava2.usecases.flatMap;

import android.util.Log;

import com.duoduolicai360.myrxjava.model.FoodDetail;
import com.duoduolicai360.myrxjava.model.FoodList;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by swg on 2017/8/23.
 */

public class RxCaseFlatMapActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCaseFlatMapActivity";

    @Override
    protected String getSubTitle() {
        return "多个网络请求依次依赖";
    }

    @Override
    protected void doSomething() {
        Rx2AndroidNetworking.get("http://www.tngou.net/api/food/list")
                .addQueryParameter("rows",1 + "")
                .build()
                .getObjectObservable(FoodList.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<FoodList>() {
                    @Override
                    public void accept(@NonNull FoodList foodList) throws Exception {
                        // 先根据获取食品列表的响应结果做一些操作
                        Log.e(TAG, "accept: doOnNext :" + foodList.toString());
                        mRxOperatorsText.append("accept: doOnNext :" + foodList.toString()+"\n");
                    }
                }).subscribeOn(Schedulers.io())
                .flatMap(new Function<FoodList, ObservableSource<FoodDetail>>() {
                    @Override
                    public ObservableSource<FoodDetail> apply(@NonNull FoodList foodList) throws Exception {
                        if (foodList != null && foodList.getTngou() != null && foodList.getTngou().size() > 0) {
                            return Rx2AndroidNetworking.post("http://www.tngou.net/api/food/show")
                                    .addBodyParameter("id", foodList.getTngou().get(0).getId() + "")
                                    .build()
                                    .getObjectObservable(FoodDetail.class);
                        }
                        return null;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodDetail>() {
                    @Override
                    public void accept(@NonNull FoodDetail foodDetail) throws Exception {
                        Log.e(TAG, "accept: success ：" + foodDetail.toString());
                        mRxOperatorsText.append("accept: success ：" + foodDetail.toString()+"\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: error :" + throwable.getMessage());
                        mRxOperatorsText.append("accept: error :" + throwable.getMessage()+"\n");
                    }
                });
    }
}
