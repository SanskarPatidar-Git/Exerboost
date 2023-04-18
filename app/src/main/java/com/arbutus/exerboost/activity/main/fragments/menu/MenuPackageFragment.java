package com.arbutus.exerboost.activity.main.fragments.menu;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arbutus.exerboost.activity.main.activity.MainActivity;
import com.arbutus.exerboost.databinding.FragmentMenuPackageBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

public class MenuPackageFragment extends Fragment {

    private FragmentMenuPackageBinding binding;

    private MenuPackageViewModel menuPackageViewModel;

    private Dialog progressDialog;

    public static MenuPackageFragment newInstance() {
        MenuPackageFragment fragment = new MenuPackageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMenuPackageBinding.inflate(inflater,container,false);

        menuPackageViewModel = new ViewModelProvider(this).get(MenuPackageViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMenuPackagesFromServer();
    }

    private void getMenuPackagesFromServer() {

        if(AppBoilerPlateCode.isInternetConnected(getContext())){
           // progressDialog = AppBoilerPlateCode.setProgressDialog(getContext());
            observeData();

        } else {
            AppBoilerPlateCode.showSnackBarForInternet(getContext(),binding.rootLayoutOfMenuPackage);
        }

    }

    private void observeData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).hideToolbar();
    }
}