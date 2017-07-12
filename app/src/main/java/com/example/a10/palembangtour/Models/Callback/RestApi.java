package com.example.a10.palembangtour.Models.Callback;

import com.example.a10.palembangtour.Models.Value;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 10 on 03/07/2017.
 */

public interface RestApi {

    @GET("read_tblAlam.php")
    Call<Value> getViewAlam();

    @GET("read_tblSejarahBudaya.php")
    Call<Value> getViewSjrBudaya();

    @GET("read_makamSultan.php")
    Call<Value> getViewMakam();

    @GET("read_tblBuatanManusia.php")
    Call<Value> getViewBuatan();

    @GET("read_tblKuliner.php")
    Call<Value> getViewKuliner();
}
