package com.arbutus.exerboost.activity.continue_new_order.fragments.address;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arbutus.exerboost.activity.continue_new_order.fragments.address.model.AddressModel;

import java.util.List;

public class SelectAddressViewModel extends ViewModel {

    private SelectAddressRepository repository = new SelectAddressRepository();
    public void getAllAddressFromServer(){
        repository.getAddressFromServer();
    }
    public LiveData<List<AddressModel>> observeAddress(){
        return repository.getAddressListMutableLiveData();
    }

    public LiveData<String> observeFailureResponse(){
        return repository.getStringFailureMutableLiveData();
    }
}
