package com.arbutus.exerboost.repository.remote;

import com.arbutus.exerboost.activity.BaseResponseModelList;
import com.arbutus.exerboost.activity.address.add_new_address.AddNewAddressModel;
import com.arbutus.exerboost.activity.auth.forgot_password.models.ForgotPasswordModel;
import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.activity.auth.login.model.response.LoginResponse;
import com.arbutus.exerboost.activity.auth.new_password.model.NewPasswordModel;
import com.arbutus.exerboost.activity.auth.register.models.request.RegisterModel;
import com.arbutus.exerboost.activity.auth.register.models.response.RegisterResponse;
import com.arbutus.exerboost.activity.auth.reset_password.model.ResetPasswordModel;
import com.arbutus.exerboost.activity.auth.social.SocialSignInModel;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.model.AddressModel;
import com.arbutus.exerboost.activity.main.fragments.home.model.Data;
import com.arbutus.exerboost.repository.BaseResponseModel;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<ResponseBody> forgotPassword(@Body  ForgotPasswordModel forgotPasswordModel);

    @POST("app/auth/verify-otp")
    Call<ResponseBody> verifyOtp(@Body ResetPasswordModel model , @Header("Authorization") String authToken);

    @POST("app/auth/update-password")
    Call<ResponseBody> updatePassword(@Body NewPasswordModel model , @Header("Authorization") String authToken);

    @GET("app/auth/logout")
    Call<ResponseBody> logOutUser(@Header("Authorization") String authToken);


    // ==================== ADDRESS ==============================
    @PATCH("app/user/update-residentialAddress")
    Call<ResponseBody> updateAddress(@Body AddNewAddressModel model);

    @GET("app/user/get-residential-address")
    Call<BaseResponseModelList<AddressModel>> getAddress(@Header("Authorization") String authToken);

    //======================= HOME FRAGMENT ===========================
    @POST("app/product/get-products")
    Call<BaseResponseModelList<Data>> getAllProducts(@Header("Authorization") String authToken);
}
