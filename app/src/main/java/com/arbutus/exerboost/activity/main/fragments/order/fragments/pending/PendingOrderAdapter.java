package com.arbutus.exerboost.activity.main.fragments.order.fragments.pending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.order.models.BaseYourOrderModel;

import java.util.List;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.ViewHolder> {

    private Context context;
    private List<BaseYourOrderModel> yourOrderModelList;

    public PendingOrderAdapter(Context context , List<BaseYourOrderModel> yourOrderModelList){
        this.context = context;
        this.yourOrderModelList = yourOrderModelList;
    }

    @NonNull
    @Override
    public PendingOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_complete_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrderAdapter.ViewHolder holder, int position) {

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
