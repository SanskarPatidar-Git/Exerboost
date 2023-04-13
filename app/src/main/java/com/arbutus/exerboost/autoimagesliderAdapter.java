package com.arbutus.exerboost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.library.sliderview.SliderViewAdapter;


public class autoimagesliderAdapter extends SliderViewAdapter<autoimagesliderAdapter.SliderViewHolder> {

    private Context context;

    public autoimagesliderAdapter(Context context) {
        this.context = context;
    }



    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.auto_image_slider, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
//        Glide.with(context)
//                .load(ApiConfiguration.IMAGE_URL + offerBanner.getImage())
//                .placeholder(R.drawable.ic_logo)
//                .error(R.drawable.logo_splash)
//                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return 1;
    }

    static class SliderViewHolder extends ViewHolder {
        View itemView;
        ImageView imageView;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewSlider);
            this.itemView = itemView;
        }
    }
}
