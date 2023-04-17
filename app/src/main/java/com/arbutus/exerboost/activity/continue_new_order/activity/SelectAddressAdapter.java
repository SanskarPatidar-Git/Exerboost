package com.arbutus.exerboost.activity.continue_new_order.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.SelectAddressViewHolder> {

    private Context context;

    public SelectAddressAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SelectAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectAddressViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SelectAddressViewHolder extends RecyclerView.ViewHolder{

        public SelectAddressViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
