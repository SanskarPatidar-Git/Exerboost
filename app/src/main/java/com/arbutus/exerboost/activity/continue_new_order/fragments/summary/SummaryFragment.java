package com.arbutus.exerboost.activity.continue_new_order.fragments.summary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.continue_new_order.activity.ContinueNewOrderActivity;
import com.arbutus.exerboost.activity.new_order.NewOrderModel;
import com.arbutus.exerboost.databinding.FragmentSummaryBinding;

public class SummaryFragment extends Fragment {

    private FragmentSummaryBinding binding;

    private NewOrderModel newOrderModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater,container,false);

        if (getArguments() != null) {
            newOrderModel = getArguments().getParcelable("order_data");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFragmentWithData();
    }

    private void initFragmentWithData() {

        binding.lostFatTextView.setText(newOrderModel.getGoal());
        binding.dayOneTextView.setText(newOrderModel.getDuration());
        binding.addressTextView.setText(newOrderModel.getStreetAddress());

    }

    @Override
    public void onResume() {
        super.onResume();
        ((ContinueNewOrderActivity)getActivity()).setHeaderTitle("Summary");
    }
}