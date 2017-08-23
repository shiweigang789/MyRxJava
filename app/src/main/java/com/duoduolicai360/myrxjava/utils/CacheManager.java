package com.duoduolicai360.myrxjava.utils;

import com.duoduolicai360.myrxjava.model.FoodList;

/**
 * Created by swg on 2017/8/23.
 */

public class CacheManager {

    private static CacheManager instance;
    private FoodList mFoodList;

    private CacheManager(){}

    public static CacheManager getInstance(){
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public FoodList getFoodListData(){
        return mFoodList;
    }

    public void setFoodListData(FoodList data){
        this.mFoodList = data;
    }

}
