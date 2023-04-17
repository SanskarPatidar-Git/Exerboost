package com.arbutus.exerboost.activity.continue_new_order.fragments.address.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.SelectAddressRadioButtonListener;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.model.AddressModel;

import java.util.List;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.ViewHolder> {

    private Context context;
    private List<AddressModel> addressModelList;

    private SelectAddressRadioButtonListener listener;

    public SelectAddressAdapter(Context context , List<AddressModel> addressModelList){
        this.context = context;
        this.addressModelList = addressModelList;
    }
    public void initListener(SelectAddressRadioButtonListener listener){
        this.listener = listener;
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

        holder.moreImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // listener.onClickMore(position);
            }
        });

        holder.addressRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                    listener.onClickRadioButton(position);
            }
        });

    }
    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView addressType , address;
        ImageView moreImageView;
        RadioButton addressRadioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.addressTextView);
            addressType = itemView.findViewById(R.id.addressTypeTextView);
            addressRadioButton = itemView.findViewById(R.id.radioAddress);
            moreImageView = itemView.findViewById(R.id.moreImageVew);
        }
    }
}
