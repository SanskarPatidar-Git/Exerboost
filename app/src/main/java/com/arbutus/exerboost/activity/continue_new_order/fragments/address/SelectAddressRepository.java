package com.arbutus.exerboost.activity.continue_new_order.fragments.address;


import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.BaseResponseModelList;
import com.arbutus.exerboost.activity.MyBaseApplication;
import com.arbutus.exerboost.activity.auth.login.model.response.LoginResponse;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.model.AddressModel;
import com.arbutus.exerboost.repository.BaseResponseModel;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.repository.remote.ApiController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAddressRepository {

    private MutableLiveData<List<AddressModel>> addressListMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> stringFailureMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<List<AddressModel>> getAddressListMutableLiveData() {
        return addressListMutableLiveData;
    }

    public MutableLiveData<String> getStringFailureMutableLiveData() {
        return stringFailureMutableLiveData;
    }


    public void getAddressFromServer(){

        String authToken = LocalSets.getAuthToken(LocalController.getInstance(MyBaseApplication.getContext()).getPreferences());

        Call<BaseResponseModelList<AddressModel>> call = ApiController.getInstance().getApiSets().getAddress(authToken);

        call.enqueue(new Callback<BaseResponseModelList<AddressModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModelList<AddressModel>> call, Response<BaseResponseModelList<AddressModel>> response) {

                BaseResponseModelList modelList = response.body();
                if(modelList!=null){

                    if(response.isSuccessful() && modelList.isStatus()){
                        addressListMutableLiveData.setValue(modelList.getData());
                    } else {
                        stringFailureMutableLiveData.setValue(modelList.getMessage());
                    }
                } else {

                    Gson gson = new GsonBuilder().create();
                    BaseResponseModelList responseModel = null;
                    try {
                        responseModel = gson.fromJson(response.errorBody().string(), BaseResponseModelList.class);
                        System.out.println("================ NULL MODEL =================== " + responseModel.getMessage());
                        stringFailureMutableLiveData.setValue(responseModel.getMessage());
                    } catch (Exception e) {
                        System.out.println("============= IO EXCEPTION ========== " + e.getMessage());
                        stringFailureMutableLiveData.setValue("Failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModelList<AddressModel>> call, Throwable t) {
                stringFailureMutableLiveData.setValue("Something went wrong");
                System.out.println("===================== ERROR ===================== "+t.getMessage());
            }
        });
    }
}
