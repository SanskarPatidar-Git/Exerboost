package com.arbutus.exerboost.activity.main.fragments.order.fragments.complete;

import android.content.Context;
import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.order.models.BaseYourOrderModel;

import java.util.List;

public class CompleteOrderAdapter extends RecyclerView.Adapter<CompleteOrderAdapter.ViewHolder> {

    private Context context;
    private List<BaseYourOrderModel> yourOrderModelList;

    public CompleteOrderAdapter(Context context , List<BaseYourOrderModel> yourOrderModelList){
        this.context = context;
        this.yourOrderModelList = yourOrderModelList;
    }

    @NonNull
    @Override
    public CompleteOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_complete_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteOrderAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return yourOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
