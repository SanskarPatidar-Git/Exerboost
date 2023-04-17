package com.arbutus.exerboost.activity.continue_new_order.fragments.payment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arbutus.exerboost.activity.continue_new_order.activity.ContinueNewOrderActivity;
import com.arbutus.exerboost.databinding.FragmentSelectPaymentBinding;

public class SelectPaymentFragment extends Fragment {

    private FragmentSelectPaymentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSelectPaymentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ContinueNewOrderActivity)getActivity()).setHeaderTitle("Select Payment");
    }
}