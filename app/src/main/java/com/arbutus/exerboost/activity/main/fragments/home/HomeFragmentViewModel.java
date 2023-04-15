package com.arbutus.exerboost.activity.main.fragments.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arbutus.exerboost.activity.main.fragments.home.model.Data;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private HomeFragmentRepository repository = new HomeFragmentRepository();

    public void getAllProductsFromRepository(){
        repository.getAllProductsFromServer();
    }

    public MutableLiveData<List<Data>> observeSuccessData(){
        return repository.getSuccessResponseMutableData();
    }

    public MutableLiveData<String> observeFailureData(){
        return repository.getFailureResponseMutableData();
    }
}
