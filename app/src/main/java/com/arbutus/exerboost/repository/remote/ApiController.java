package com.arbutus.exerboost.repository.remote;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.arbutus.exerboost.activity.MyBaseApplication;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.utilities.AppConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    private Retrofit retrofit;
    public static ApiController controller;

    private ApiController(){

        //Get token from shared preference
        String authToken = LocalSets.getAuthToken(LocalController.getInstance(MyBaseApplication.getContext()).getPreferences());
        System.out.println("============= TOKEN ================ "+authToken);

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
