package com.example.a10.palembangtour.Models.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.a10.palembangtour.Models.MenuModel;
import com.example.a10.palembangtour.R;
import com.example.a10.palembangtour.UI.ListActivity;

import java.util.List;

/**
 * Created by 10 on 03/07/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private Context mCtx;
    private List<MenuModel> listMenu;

    public MenuAdapter(Context mCtx, List<MenuModel> listMenu) {
        this.mCtx = mCtx;
        this.listMenu = listMenu;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewMenu = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu, parent, false);
        return new MenuHolder(viewMenu);
    }

    @Override
    public void onBindViewHolder(final MenuHolder holder, int position) {
        final MenuModel menu = listMenu.get(position);

        holder.mTextView.setText(menu.getTitle());

        Glide.with(mCtx).load(menu.getImage()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.mImageView.setImageBitmap(resource);
            }
        });

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ListActivity.class);
                intent.putExtra("title", menu.getTitle());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;

        public MenuHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.imgBg);
            mTextView = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
