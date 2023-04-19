package com.arbutus.exerboost.activity.main.fragments.order;

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
import com.arbutus.exerboost.activity.main.fragments.order.fragments.complete.CompleteOrderFragment;
import com.arbutus.exerboost.activity.main.fragments.order.fragments.pending.PendingOrderFragment;
import com.arbutus.exerboost.databinding.FragmentYourOrderBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class YourOrderFragment extends Fragment {

    

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
        binding = FragmentYourOrderBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadTabLayoutFragment();
        initListener();
    }

    private void loadTabLayoutFragment() {

            String arr[]=new String[]{"Complete orders","Pending orders"};

            completeOrderFragment = CompleteOrderFragment.newInstance();
            pendingOrderFragment = PendingOrderFragment.newInstance();

            TabLayoutAdapter adapter=new TabLayoutAdapter(this,completeOrderFragment,pendingOrderFragment);
            binding.viewPager.setAdapter(adapter);

            new TabLayoutMediator(binding.orderTabLayout,binding.viewPager,(tab, position) -> tab.setText(arr[position])).attach();
    }

    private void initListener(){

        binding.header.headerBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.header.headerTitleTextView.setText("Your Orders");
        ((MainActivity)getActivity()).hideToolbar();
    }
}