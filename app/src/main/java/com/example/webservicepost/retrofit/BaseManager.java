package com.example.webservicepost.retrofit;


import com.example.webservicepost.restapi.RestApi;

public class BaseManager {

    protected RestApi getRestApiClient() {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.BASE_URL);
        return restApiClient.getRestApi();
    }
}
