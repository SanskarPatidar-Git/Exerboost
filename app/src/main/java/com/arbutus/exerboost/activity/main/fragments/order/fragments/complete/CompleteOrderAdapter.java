package com.arbutus.exerboost.activity.main.fragments.order.fragments.complete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.order.fragments.complete.listener.CompleteOrderListener;
import com.arbutus.exerboost.activity.main.fragments.order.models.response.YourOrderRootModel;

import java.util.List;

public class CompleteOrderAdapter extends RecyclerView.Adapter<CompleteOrderAdapter.ViewHolder> {

    private Context context;
    private List<YourOrderRootModel> yourOrderModelList;
    private CompleteOrderListener listener;

    public CompleteOrderAdapter(Context context , List<YourOrderRootModel> yourOrderModelList){
        this.context = context;
        this.yourOrderModelList = yourOrderModelList;
    }

    public void initListener(CompleteOrderListener listener){
        this.listener = listener ;
    }

    @NonNull
    @Override
    public CompleteOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_complete_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteOrderAdapter.ViewHolder holder,  int position) {

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

        holder.orderAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickOrderAgain(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return yourOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView orderTitle , orderDescription , orderCountItems , orderPrice , orderAgain;
        ImageButton removeItemCountImageButton , addItemCountImageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderTitle = itemView.findViewById(R.id.orderTitleTextView);
            orderDescription = itemView.findViewById(R.id.orderDescriptionTextView);
            orderCountItems = itemView.findViewById(R.id.totalOrderCountTextView);
            orderPrice = itemView.findViewById(R.id.orderPriceTextView);
            orderAgain = itemView.findViewById(R.id.orderAgainTextView);

            removeItemCountImageButton = itemView.findViewById(R.id.removeBtn);
            addItemCountImageButton = itemView.findViewById(R.id.addBtn);
        }
    }
}
