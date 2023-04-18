package com.arbutus.exerboost.activity.main.fragments.home;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arbutus.exerboost.activity.main.fragments.home.adapters.AllProductAdapter;
import com.arbutus.exerboost.activity.main.fragments.home.adapters.AutoImageSlideAdapter;
import com.arbutus.exerboost.activity.main.fragments.home.model.Data;
import com.arbutus.exerboost.activity.main.fragments.menu.MenuPackageFragment;
import com.arbutus.exerboost.activity.new_order.NewOrderActivity;
import com.arbutus.exerboost.databinding.FragmentHomeBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeFragmentViewModel homeFragmentViewModel;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater,container,false);

        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        binding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSliderAdapter();
        initListener();
        getAllProductsFromViewModel();
    }

    private void initListener(){

        binding.lunchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppBoilerPlateCode.navigateToActivity(getActivity(), NewOrderActivity.class,null);
            }
        });

        binding.dinnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppBoilerPlateCode.navigateToActivity(getActivity(), NewOrderActivity.class,null);
            }
        });
    }

    private void getAllProductsFromViewModel() {

        Dialog progressDialog = AppBoilerPlateCode.setProgressDialog(getContext());

        homeFragmentViewModel.getAllProductsFromRepository();

        LiveData<List<Data>> dataLiveDataList = homeFragmentViewModel.observeSuccessData();

        dataLiveDataList.observe(getViewLifecycleOwner(), new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                progressDialog.dismiss();
                setProductAdapter(data);
            }
        });

        LiveData<String> stringLiveData = homeFragmentViewModel.observeFailureData();
        stringLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                System.out.println("============= Error =================== "+s);
            }
        });
    }

    private void setProductAdapter(List<Data> data) {

        AllProductAdapter adapter = new AllProductAdapter(getContext(),data);
        binding.recyclerViewCategories.setAdapter(adapter);
    }

    private void setSliderAdapter() {
        AutoImageSlideAdapter adapter = new AutoImageSlideAdapter(getContext());
        binding.imageSlider.setSliderAdapter(adapter);
    }
}