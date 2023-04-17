package com.arbutus.exerboost.activity.main.fragments.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arbutus.exerboost.R;
import com.library.sliderview.SliderViewAdapter;


public class AutoImageSlideAdapter extends SliderViewAdapter<AutoImageSlideAdapter.SliderViewHolder> {

    private Context context;

    public AutoImageSlideAdapter(Context context) {
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
        viewHolder.imageView.setImageResource(R.drawable.home_image_slider_1);
    }

    @Override
    public int getCount() {
        return 3;
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
