package com.arbutus.exerboost.activity.order.fragments.address;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.databinding.FragmentSelectAddressBinding;

public class SelectAddressFragment extends Fragment {

    private FragmentSelectAddressBinding binding;

    private SelectAddressViewModel selectAddressViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSelectAddressBinding.inflate(inflater,container,false);

        selectAddressViewModel = new ViewModelProvider(this).get(SelectAddressViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}