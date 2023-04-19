package com.arbutus.exerboost.activity.main.fragments.order.fragments.pending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.order.fragments.pending.listener.PendingOrderListener;
import com.arbutus.exerboost.activity.main.fragments.order.models.response.YourOrderRootModel;

import java.util.List;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.ViewHolder> {

    private Context context;
    private List<YourOrderRootModel> yourOrderModelList;

    private PendingOrderListener listener;

    public PendingOrderAdapter(Context context , List<YourOrderRootModel> yourOrderModelList){
        this.context = context;
        this.yourOrderModelList = yourOrderModelList;
    }

    public void initListener(PendingOrderListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public PendingOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_pending_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrderAdapter.ViewHolder holder, int position) {

        /*
        holder.orderTitle.setText();
        holder.orderDescription.setText();
        holder.orderPrice.setText();
         */

        holder.addItemCountImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickAddItem(position);
            }
        });

        holder.removeItemCountImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickRemoveItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return yourOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView orderTitle , orderDescription , orderCountItems , orderPrice;
        ImageButton removeItemCountImageButton , addItemCountImageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderTitle = itemView.findViewById(R.id.orderTitleTextView);
            orderDescription = itemView.findViewById(R.id.orderDescriptionTextView);
            orderCountItems = itemView.findViewById(R.id.totalOrderCountTextView);
            orderPrice = itemView.findViewById(R.id.orderPriceTextView);

            removeItemCountImageButton = itemView.findViewById(R.id.removeBtn);
            addItemCountImageButton = itemView.findViewById(R.id.addBtn);
        }
    }
}
