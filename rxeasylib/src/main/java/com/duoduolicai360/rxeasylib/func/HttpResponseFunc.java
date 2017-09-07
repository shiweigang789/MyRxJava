package com.duoduolicai360.rxeasylib.func;

import android.support.annotation.NonNull;

import com.duoduolicai360.rxeasylib.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ApiException.handleException(throwable));
    }
}