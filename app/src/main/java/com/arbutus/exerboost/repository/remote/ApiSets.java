package com.arbutus.exerboost.repository.remote;

import com.arbutus.exerboost.activity.add_new_address.AddNewAddressModel;
import com.arbutus.exerboost.activity.auth.forgot_password.ForgotPasswordModel;
import com.arbutus.exerboost.activity.auth.forgot_password.ForgotPasswordResponse;
import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.activity.auth.login.model.response.LoginResponse;
import com.arbutus.exerboost.activity.auth.register.models.request.RegisterModel;
import com.arbutus.exerboost.activity.auth.register.models.response.RegisterResponse;
import com.arbutus.exerboost.activity.auth.social.SocialSignInModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ApiSets {

    // ====================== AUTHENTICATION ========================
    @POST("app/auth/login")
    Call<LoginResponse> loginUser(@Body LoginModel loginModel);

    @POST("app/auth/signup")
    Call<RegisterResponse> registerUser(@Body RegisterModel registerModel);

    @POST("app/auth/social-login")
    Call<LoginResponse> loginUserWithSocial(@Body SocialSignInModel model);

    @PATCH("app/user/update-residentialAddress")
    Call<ResponseBody> addNewAddress(@Body AddNewAddressModel model);

    @POST("app/auth/forgot-password")
    Call<ForgotPasswordResponse> forgotPasswordUser(ForgotPasswordModel forgotPasswordModel);
}
