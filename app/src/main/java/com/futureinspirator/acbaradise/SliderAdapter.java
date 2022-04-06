package com.futureinspirator.acbaradise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    private ImageView slideimageview;
    private TextView slideheadingview;
    private TextView slidepriceview;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //array
    public int[] slide_image = {
          R.drawable.fire,
            R.drawable.water,
            R.drawable.air

    };

    public String[] slide_headings = {
            "FIRE",
            "SPLASH",
            "BREEZE"
    };
    public String[] slide_price = {
            "399 /YEAR",
            "199 /MONTH",
            "99 /MONTH"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        slideimageview = view.findViewById(R.id.slide_image);
        slideheadingview =view.findViewById(R.id.title_main);
        slidepriceview = view.findViewById(R.id.slide_price);


        slideimageview.setImageResource(slide_image[position]);
        slideheadingview.setText(slide_headings[position]);
        slidepriceview.setText(slide_price[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
