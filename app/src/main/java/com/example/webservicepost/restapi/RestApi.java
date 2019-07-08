package com.example.webservicepost.restapi;


import com.example.webservicepost.model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/insert.php")
    Call<Result> addUser(@Field("ad") String ad ,@Field("soyad") String soyad);

}
