package com.arbutus.exerboost.activity.main.fragments.order.fragments.pending;

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

import com.arbutus.exerboost.activity.main.fragments.order.fragments.pending.listener.PendingOrderListener;
import com.arbutus.exerboost.activity.main.fragments.order.models.response.YourOrderRootModel;
import com.arbutus.exerboost.activity.main.fragments.order.models.request.YourOrderRequestModel;
import com.arbutus.exerboost.databinding.FragmentPendingOrderBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

import java.util.List;


public class PendingOrderFragment extends Fragment {

    private FragmentPendingOrderBinding binding;

    private PendingOrderViewModel pendingOrderViewModel;

    private Dialog progressDialog;

    public static PendingOrderFragment newInstance() {
        PendingOrderFragment fragment = new PendingOrderFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pendingOrderViewModel = new ViewModelProvider(this).get(PendingOrderViewModel.class);

        // Inflate the layout for this fragment
        binding = FragmentPendingOrderBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = AppBoilerPlateCode.setProgressDialog(getContext());

        //========== GET COMPLETE ORDER FROM VIEW MODEL =====================
        getOrderFromViewModel();
    }

    private void getOrderFromViewModel() {

        YourOrderRequestModel yourOrderRequestModel = new YourOrderRequestModel("pending");
        pendingOrderViewModel.getCompleteOrderFromRepository(yourOrderRequestModel);


        //================================ OBSERVE DATA FROM VIEW MODEL ==============================

        LiveData<List<YourOrderRootModel>> completedOrderList = pendingOrderViewModel.getYourCompletedOrderModelMutableLiveData();

        completedOrderList.observe(getViewLifecycleOwner(), new Observer<List<YourOrderRootModel>>() {
            @Override
            public void onChanged(List<YourOrderRootModel> yourOrderRootModels) {
                progressDialog.dismiss();

                //=========== SET ADAPTER =============
                if(yourOrderRootModels.size()>0){

                    binding.noPendingOrderFoundTextView.setVisibility(View.GONE);
                    setAdapter(yourOrderRootModels);
                } else {

                    binding.noPendingOrderFoundTextView.setVisibility(View.VISIBLE);
                }

            }
        });

        LiveData<String> failureLiveData = pendingOrderViewModel.getFailureStringMutableLiveData();
        failureLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                System.out.println("================= FAILED ================ "+s);
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<YourOrderRootModel> yourOrderRootModels) {

        PendingOrderAdapter adapter = new PendingOrderAdapter(getContext(),yourOrderRootModels);
        binding.completeOrderRecyclerView.setAdapter(adapter);

        adapter.initListener(new PendingOrderListener() {
            @Override
            public void onClickRemoveItem(int position) {

            }

            @Override
            public void onClickAddItem(int position) {

            }
        });
    }
}