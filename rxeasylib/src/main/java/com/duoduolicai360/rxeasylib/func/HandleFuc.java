package com.duoduolicai360.rxeasylib.func;

import android.support.annotation.NonNull;

import com.duoduolicai360.rxeasylib.exception.ApiException;
import com.duoduolicai360.rxeasylib.exception.ServerException;
import com.duoduolicai360.rxeasylib.model.ApiResult;

import io.reactivex.functions.Function;

/**
 * Created by swg on 2017/9/7.
 */

public class HandleFuc<T> implements Function<ApiResult<T>, T> {
    @Override
    public T apply(@NonNull ApiResult<T> tApiResult) throws Exception {
        if (ApiException.isOk(tApiResult)) {
            return tApiResult.getData();// == null ? Optional.ofNullable(tApiResult.getData()).orElse(null) : tApiResult.getData();
        } else {
            throw new ServerException(tApiResult.getCode(), tApiResult.getMsg());
        }
    }
}