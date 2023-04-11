package com.arbutus.exerboost.repository.remote;

import com.arbutus.exerboost.activity.auth.login.model.LoginModel;
import com.arbutus.exerboost.activity.auth.login.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiSets {

    @POST("app/auth/login")
    Call<LoginResponse> loginUser(@Body LoginModel loginModel);
}
