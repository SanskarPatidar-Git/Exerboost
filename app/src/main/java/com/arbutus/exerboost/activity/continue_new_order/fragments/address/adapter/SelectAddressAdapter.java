package com.arbutus.exerboost.activity.continue_new_order.fragments.address.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.model.AddressModel;

import java.util.List;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.ViewHolder> {

    private Context context;
    private List<AddressModel> addressModelList;

    public SelectAddressAdapter(Context context , List<AddressModel> addressModelList){
        this.context = context;
        this.addressModelList = addressModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_select_address,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AddressModel addressModel = addressModelList.get(position);
        holder.addressType.setText(addressModel.getAddressType());
        holder.address.setText(addressModel.getStreet1Address());
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView addressType , address;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.addressTextView);
            addressType = itemView.findViewById(R.id.addressTypeTextView);
        }
    }
}
