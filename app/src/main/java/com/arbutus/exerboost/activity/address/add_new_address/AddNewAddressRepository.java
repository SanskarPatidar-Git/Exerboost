package com.arbutus.exerboost.activity.address.add_new_address;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.repository.remote.ApiController;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewAddressRepository {

    private MutableLiveData<String> failureResponseMutableData = new MutableLiveData<>();
    private MutableLiveData<String> successResponseMutableData = new MutableLiveData<>();

    public LiveData<String> getFailureResponseMutableData() {
        return failureResponseMutableData;
    }

    public LiveData<String> getSuccessResponseMutableData() {
        return successResponseMutableData;
    }

    public void addNewAddress(AddNewAddressModel model){

        Call<ResponseBody> call = null; //= ApiController.getInstance().getApiSets().addNewAddress(model);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){

                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if(object.getBoolean("status")){
                            successResponseMutableData.setValue(object.getString("message"));
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
                System.out.println("================ ERROR =================== " + t.getMessage());
                failureResponseMutableData.setValue("Error");
            }
        });
    }
}