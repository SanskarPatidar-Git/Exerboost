package com.arbutus.exerboost.activity.main.fragments.order.fragments.pending;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.order.fragments.complete.CompleteOrderAdapter;
import com.arbutus.exerboost.activity.main.fragments.order.models.BaseYourOrderModel;
import com.arbutus.exerboost.databinding.FragmentMenuPackageBinding;
import com.arbutus.exerboost.databinding.FragmentPendingOrderBinding;

import java.util.List;


public class PendingOrderFragment extends Fragment {

    private FragmentPendingOrderBinding binding;

    public static PendingOrderFragment newInstance() {
        PendingOrderFragment fragment = new PendingOrderFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentPendingOrderBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    public void setCompletedData(List<BaseYourOrderModel> pendingOrderList){

        if(pendingOrderList.size()>0){

            //binding.noPendingOrderFoundTextView.setVisibility(View.GONE);

            PendingOrderAdapter adapter = new PendingOrderAdapter(getContext(),pendingOrderList);
            binding.completeOrderRecyclerView.setAdapter(adapter);
        } else {
            //binding.noPendingOrderFoundTextView.setVisibility(View.VISIBLE);
        }

    }
}