package com.duoduolicai360.myrxjava.net.api;

import com.duoduolicai360.myrxjava.model.CategoryResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by swg on 2017/8/23.
 */

public interface GanApi {

    @GET("data/{category}/{count}/{page}")
    Observable<CategoryResult> getCategoryData(@Path("category")String category, @Path("count")int count, @Path("page")int page);

}
