package com.arbutus.exerboost.activity.intro;

import android.content.Context;
import android.hardware.lights.LightsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.arbutus.exerboost.R;

import java.util.ArrayList;
import java.util.List;

public class IntroSliderAdapter extends PagerAdapter {

    private Context context;
    private List<IntroModel> introModelList;

    public IntroSliderAdapter(Context context , ArrayList<IntroModel> introModel){
        this.context = context;
        this.introModelList = introModel;
    }

    @Override
    public int getCount() {
        return introModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view  = inflater.inflate(R.layout.layout_intro_slider_view_pager,container,false);

        IntroModel model = introModelList.get(position);

        TextView text = view.findViewById(R.id.sliderTextView);
        ImageView image = view.findViewById(R.id.sliderImageView);

        text.setText(model.getText());
        image.setBackgroundResource(model.getImageId());

        container.addView(view);
        return  view;
    }
}
