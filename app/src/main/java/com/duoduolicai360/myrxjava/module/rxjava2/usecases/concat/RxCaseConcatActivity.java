package com.duoduolicai360.myrxjava.module.rxjava2.usecases.concat;

import android.util.Log;

import com.duoduolicai360.myrxjava.model.FoodList;
import com.duoduolicai360.myrxjava.module.rxjava2.operators.RxOperatorBaseActivity;
import com.duoduolicai360.myrxjava.utils.CacheManager;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by swg on 2017/8/23.
 */

public class RxCaseConcatActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCaseConcatActivity";
    private boolean isFromNet = false;

    @Override
    protected String getSubTitle() {
        return "先读取缓存再读取网络";
    }

    @Override
    protected void doSomething() {
        Observable<FoodList> cache = Observable.create(new ObservableOnSubscribe<FoodList>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<FoodList> e) throws Exception {
                Log.e(TAG, "create当前线程:" + Thread.currentThread().getName());
                FoodList data = CacheManager.getInstance().getFoodListData();
                if (data != null) {
                    isFromNet = false;
                    Log.e(TAG, "\nsubscribe: 读取缓存数据:");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRxOperatorsText.append("\nsubscribe: 读取缓存数据:\n");
                        }
                    });
                    e.onNext(data);
                } else {
                    isFromNet = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRxOperatorsText.append("\nsubscribe: 读取网络数据:\n");
                        }
                    });
                    Log.e(TAG, "\nsubscribe: 读取网络数据:");
                    e.onComplete();
                }
            }
        });

        Observable<FoodList> network = Rx2AndroidNetworking.get("http://www.tngou.net/api/food/list")
                .addQueryParameter("rows", 10 + "")
                .build()
                .getObjectObservable(FoodList.class);

        Observable.concat(cache,network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodList>() {
                    @Override
                    public void accept(@NonNull FoodList tngouBeen) throws Exception {
                        Log.e(TAG, "subscribe 成功:"+Thread.currentThread().getName() );
                        if (isFromNet){
                            mRxOperatorsText.append("accept : 网络获取数据设置缓存: \n");
                            Log.e(TAG, "accept : 网络获取数据设置缓存: \n"+tngouBeen.toString() );
                            CacheManager.getInstance().setFoodListData(tngouBeen);
                        }

                        mRxOperatorsText.append("accept: 读取数据成功:" + tngouBeen.toString()+"\n");
                        Log.e(TAG, "accept: 读取数据成功:" + tngouBeen.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 失败:"+Thread.currentThread().getName() );
                        Log.e(TAG, "accept: 读取数据失败："+throwable.getMessage() );
                        mRxOperatorsText.append("accept: 读取数据失败："+throwable.getMessage()+"\n");
                    }
                });

    }
}
