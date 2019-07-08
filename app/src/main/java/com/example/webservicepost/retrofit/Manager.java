package com.example.webservicepost.retrofit;


import com.example.webservicepost.model.Result;

import retrofit2.Call;

public class Manager extends BaseManager {

    private static Manager ourInstance = new Manager();

    public static synchronized Manager getInstance() {
        return ourInstance;
    }

    public Call<Result> addUser(String ad,String soyad){

        Call<Result> call = getRestApiClient().addUser(ad,soyad);
        return call;
    }


}
