package com.arbutus.exerboost.activity.main.fragments.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.home.model.Data;

import java.util.ArrayList;
import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Data> productDataList ;

    public AllProductAdapter(Context context , List<Data> productDataList){
        this.context = context;
        this.productDataList = productDataList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_home_page,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Data data = productDataList.get(position);
        holder.productName.setText(data.getName());
        holder.productCategory.setText(data.getCategory());
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView productName , productCategory;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productCategory = itemView.findViewById(R.id.productCategory);
        }
    }
}
