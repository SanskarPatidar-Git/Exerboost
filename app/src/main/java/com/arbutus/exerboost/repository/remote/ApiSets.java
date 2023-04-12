package com.arbutus.exerboost.repository.remote;

import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.activity.auth.login.model.response.LoginResponse;
import com.arbutus.exerboost.activity.auth.register.models.request.RegisterModel;
import com.arbutus.exerboost.activity.auth.register.models.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiSets {

    // ====================== AUTHENTICATION ========================
    @POST("app/auth/login")
    Call<LoginResponse> loginUser(@Body LoginModel loginModel);

    @POST("app/auth/signup")
    Call<RegisterResponse> registerUser(@Body RegisterModel registerModel);
}
