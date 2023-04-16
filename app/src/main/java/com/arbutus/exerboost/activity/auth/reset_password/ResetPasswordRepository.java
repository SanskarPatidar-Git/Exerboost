package com.arbutus.exerboost.activity.auth.reset_password;

import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.auth.reset_password.model.ResetPasswordModel;
import com.arbutus.exerboost.repository.remote.ApiController;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordRepository {

    private MutableLiveData<String> failureResponseMutableData = new MutableLiveData<>();

    private MutableLiveData<String> successResponseMutableData = new MutableLiveData<>();

    private MutableLiveData<String> authTokenMutableData = new MutableLiveData<>();


    public MutableLiveData<String> getFailureResponseMutableData() {
        return failureResponseMutableData;
    }

    public MutableLiveData<String> getSuccessResponseMutableData() {
        return successResponseMutableData;
    }

    public MutableLiveData<String> resetPassword(ResetPasswordModel model , String authToken)  {

        Call<ResponseBody> call = ApiController.getInstance().getApiSets().verifyOtp(model,authToken);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){

                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if(object.getBoolean("status")){

                            JSONObject dataObject = object.getJSONObject("data");
                            successResponseMutableData.setValue(object.getString("message"));
                            authTokenMutableData.setValue(dataObject.getString("token"));

                        } else {
                            failureResponseMutableData.setValue(object.getString("message"));
                        }
                    } catch (Exception e) {
                        System.out.println("--------------- Exception ------------------"+e.getMessage());
                        failureResponseMutableData.setValue("Failed");
                    }
                } else {
                    System.out.println("=========== error ========== "+response.message());
                    failureResponseMutableData.setValue("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("=========== ERROR ============== "+t.getMessage());
                failureResponseMutableData.setValue("Something went wrong");
            }
        });
        return authTokenMutableData;
    }
}
