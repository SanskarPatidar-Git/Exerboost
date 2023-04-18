package com.arbutus.exerboost.activity.main.fragments.order;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.activity.MainActivity;
import com.arbutus.exerboost.activity.main.fragments.order.fragments.complete.CompleteOrderFragment;
import com.arbutus.exerboost.activity.main.fragments.order.fragments.pending.PendingOrderFragment;
import com.arbutus.exerboost.activity.main.fragments.order.models.BaseYourOrderModel;
import com.arbutus.exerboost.activity.main.fragments.order.viewmodel.YourOrderViewModel;
import com.arbutus.exerboost.databinding.FragmentYourOrderBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class YourOrderFragment extends Fragment {


    private YourOrderViewModel yourOrderViewModel;

    private Dialog progressDialog;
    private FragmentYourOrderBinding binding;

    private CompleteOrderFragment completeOrderFragment;
    private PendingOrderFragment pendingOrderFragment;


    public static YourOrderFragment newInstance() {
        YourOrderFragment fragment = new YourOrderFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        yourOrderViewModel = new ViewModelProvider(this).get(YourOrderViewModel.class);
        binding = FragmentYourOrderBinding.inflate(inflater,container,false);
        binding.header.headerTitleTextView.setText("Your Orders");
        loadTabLayoutFragment();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        observeDataFromViewModel();
    }

    private void loadTabLayoutFragment() {

            String arr[]=new String[]{"Complete orders","Pending orders"};

            completeOrderFragment = CompleteOrderFragment.newInstance();
            pendingOrderFragment = PendingOrderFragment.newInstance();

            TabLayoutAdapter adapter=new TabLayoutAdapter(this,completeOrderFragment,pendingOrderFragment);
            binding.viewPager.setAdapter(adapter);

            new TabLayoutMediator(binding.orderTabLayout,binding.viewPager,(tab, position) -> tab.setText(arr[position])).attach();
    }

    private void observeDataFromViewModel(){

        progressDialog = AppBoilerPlateCode.setProgressDialog(getContext());

        yourOrderViewModel.getCompleteOrder();
        LiveData<List<BaseYourOrderModel>> completedOrderList = yourOrderViewModel.getYourCompletedOrderModelMutableLiveData();
        LiveData<List<BaseYourOrderModel>> pendingOrderList = yourOrderViewModel.getYourPendingOrderModelMutableLiveData();

        LiveData<String> failureLiveData = yourOrderViewModel.getFailureStringMutableLiveData();
        completedOrderList.observe(getViewLifecycleOwner(), new Observer<List<BaseYourOrderModel>>() {
            @Override
            public void onChanged(List<BaseYourOrderModel> baseYourOrderModels) {
                progressDialog.dismiss();
                completeOrderFragment.setCompletedData(baseYourOrderModels);
            }
        });

        pendingOrderList.observe(getViewLifecycleOwner(), new Observer<List<BaseYourOrderModel>>() {
            @Override
            public void onChanged(List<BaseYourOrderModel> baseYourOrderModels) {
                progressDialog.dismiss();
                pendingOrderFragment.setCompletedData(baseYourOrderModels);
            }
        });

        failureLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                System.out.println("================= FAILED ================ "+s);
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).hideToolbar();
    }
}