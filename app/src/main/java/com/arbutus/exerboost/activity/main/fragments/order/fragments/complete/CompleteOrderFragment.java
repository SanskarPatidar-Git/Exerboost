package com.arbutus.exerboost.activity.main.fragments.order.fragments.complete;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arbutus.exerboost.activity.main.fragments.order.models.BaseYourOrderModel;
import com.arbutus.exerboost.activity.main.fragments.order.viewmodel.YourOrderViewModel;
import com.arbutus.exerboost.databinding.FragmentCompleteOrderBinding;

import java.util.List;


public class CompleteOrderFragment extends Fragment {

    private FragmentCompleteOrderBinding binding;

    private YourOrderViewModel yourOrderViewModel;


    public static CompleteOrderFragment newInstance() {
        CompleteOrderFragment fragment = new CompleteOrderFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentCompleteOrderBinding.inflate(inflater,container,false);

        yourOrderViewModel = new ViewModelProvider(this).get(YourOrderViewModel.class);
        return binding.getRoot();
    }

    public void setCompletedData(List<BaseYourOrderModel> completedYourModelList){

        if(completedYourModelList.size()>0){

            binding.noCompletedOrderFoundTextView.setVisibility(View.GONE);

            CompleteOrderAdapter adapter = new CompleteOrderAdapter(getContext(),completedYourModelList);
            binding.completeOrderRecyclerView.setAdapter(adapter);
        } else {
            binding.noCompletedOrderFoundTextView.setVisibility(View.VISIBLE);
        }

    }
}