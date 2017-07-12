package com.example.a10.palembangtour.Controller;

import android.util.Log;

import com.example.a10.palembangtour.Models.Callback.RestApi;
import com.example.a10.palembangtour.Models.helper.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 10 on 03/07/2017.
 */

public class RestManager {

    private RestApi mRestService;

    public RestApi getmRestService() {

        if (mRestService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mRestService = retrofit.create(RestApi.class);

            Log.d("Rest : " , mRestService.toString());
        }
        return mRestService;
    }

}
