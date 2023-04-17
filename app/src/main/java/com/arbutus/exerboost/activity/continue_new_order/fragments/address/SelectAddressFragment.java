package com.arbutus.exerboost.activity.continue_new_order.fragments.address;

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

import com.arbutus.exerboost.BlankFragment;
import com.arbutus.exerboost.activity.address.add_new_address.AddNewAddressActivity;
import com.arbutus.exerboost.activity.continue_new_order.SelectedAddressListener;
import com.arbutus.exerboost.activity.continue_new_order.activity.ContinueNewOrderActivity;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.adapter.SelectAddressAdapter;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.model.AddressModel;
import com.arbutus.exerboost.databinding.FragmentSelectAddressBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.FragmentController;

import java.util.List;

public class SelectAddressFragment extends Fragment {

    private FragmentSelectAddressBinding binding;

    private SelectAddressViewModel selectAddressViewModel;

    private Dialog progressDialog;

    private SelectedAddressListener listener;

    public static SelectAddressFragment newInstance() {
        SelectAddressFragment fragment = new SelectAddressFragment();
        return fragment;
    }

    public void initSelectAddressListener(SelectedAddressListener listener){
        this.listener = listener;
    }
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

        binding.addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initListener();
    }

    private void initListener() {

        binding.addNewAddressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppBoilerPlateCode.navigateToActivity(getActivity(), AddNewAddressActivity.class,null);
            }
        });
    }

    private void getAddressFromServer() {

        progressDialog = AppBoilerPlateCode.setProgressDialog(getContext());

        selectAddressViewModel.getAllAddressFromServer();

        LiveData<List<AddressModel>> addressDataList = selectAddressViewModel.observeAddress();
        addressDataList.observe(getViewLifecycleOwner(), new Observer<List<AddressModel>>() {
            @Override
            public void onChanged(List<AddressModel> addressModels) {
                progressDialog.dismiss();

                if(addressModels.size()>0){
                    binding.noAddressFoundTextView.setVisibility(View.GONE);
                    setAdapter(addressModels);
                } else {
                   binding.noAddressFoundTextView.setVisibility(View.VISIBLE);
                }

            }
        });

        LiveData<String> failureResponse = selectAddressViewModel.observeFailureResponse();
        failureResponse.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                System.out.println("======== NO ADDRESS FOUND ==============");
            }
        });
    }

    private void setAdapter(List<AddressModel> addressModels){

        SelectAddressAdapter adapter = new SelectAddressAdapter(getContext(),addressModels);
        adapter.initListener(new SelectAddressRadioButtonListener() {
            @Override
            public void onClickRadioButton(int position) {
               listener.onClickAddress(addressModels.get(position).getAddressId() , addressModels.get(position).getStreet1Address());
            }

            @Override
            public void onClickMore(int position) {

            }
        });
        binding.addressRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        ((ContinueNewOrderActivity)getActivity()).setHeaderTitle("Select Address");
        getAddressFromServer();
    }
}