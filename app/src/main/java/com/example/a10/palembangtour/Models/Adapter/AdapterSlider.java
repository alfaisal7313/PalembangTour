package com.example.a10.palembangtour.Models.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a10.palembangtour.R;

import java.util.ArrayList;

/**
 * Created by 10 on 11/07/2017.
 */

public class AdapterSlider extends PagerAdapter {

    private Context mContext;
    private ArrayList<Integer> image;
    private LayoutInflater layoutInflater;

    public AdapterSlider(Context mContext, ArrayList<Integer> image) {
        this.mContext = mContext;
        this.image = image;
        layoutInflater = layoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Integer slide = image.get(position);
        View view = layoutInflater.inflate(R.layout.slider_image, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageSlide);
        Glide.with(mContext).load(slide).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
