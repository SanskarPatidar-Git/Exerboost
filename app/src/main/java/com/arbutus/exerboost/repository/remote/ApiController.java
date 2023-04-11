package com.arbutus.exerboost.repository.remote;

import com.arbutus.exerboost.utilities.AppConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    private Retrofit retrofit;
    public static ApiController controller;

    private ApiController(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiController getInstance(){
        if(controller == null){
            controller = new ApiController();
        }
        return controller;
    }

    public ApiSets getApiSets(){
        return retrofit.create(ApiSets.class);
    }
}
