package com.arbutus.exerboost.repository.remote;

import com.arbutus.exerboost.activity.BaseResponseModelList;
import com.arbutus.exerboost.activity.address.add_new_address.AddNewAddressModel;
import com.arbutus.exerboost.activity.auth.forgot_password.ForgotPasswordModel;
import com.arbutus.exerboost.activity.auth.forgot_password.ForgotPasswordResponse;
import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.activity.auth.login.model.response.LoginResponse;
import com.arbutus.exerboost.activity.auth.register.models.request.RegisterModel;
import com.arbutus.exerboost.activity.auth.register.models.response.RegisterResponse;
import com.arbutus.exerboost.activity.auth.social.SocialSignInModel;
import com.arbutus.exerboost.activity.main.fragments.home.model.Data;
import com.arbutus.exerboost.repository.BaseResponseModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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

    @POST("app/auth/forgot-password")
    Call<ForgotPasswordResponse> forgotPasswordUser(ForgotPasswordModel forgotPasswordModel);


    // ==================== ADDRESS ==============================
    @PATCH("app/user/update-residentialAddress")
    Call<ResponseBody> updateAddress(@Body AddNewAddressModel model);


    //======================= HOME FRAGMENT ===========================
    @POST("app/product/get-products")
    Call<BaseResponseModelList<Data>> getAllProducts(@Header("Authorization") String authToken);
}
