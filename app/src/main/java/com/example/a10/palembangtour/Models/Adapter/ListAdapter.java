package com.example.a10.palembangtour.Models.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a10.palembangtour.Models.Result;
import com.example.a10.palembangtour.R;
import com.example.a10.palembangtour.UI.DetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 10 on 03/07/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private Context context;
    private List<Result> results;

    public ListAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListHolder holder, int position) {

        final Result list = results.get(position);
        holder.judul.setText(list.getNama());
        holder.lokasi.setText(list.getLokasi());

        if (list.isDatabase()){
            holder.mImageViewList.setImageBitmap(list.getPicture());
        }else {
            Glide.with(context).load(list.getImage()).asBitmap().placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.mImageViewList);
        }
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", holder.judul.getText().toString());
                intent.putExtra("images", list.getImage());
                intent.putExtra("dayaTarik", list.getDayaTarik());
                intent.putExtra("fasilitas", list.getFasilitas());
                intent.putExtra("pengelola", list.getPengelola());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class ListHolder extends RecyclerView.ViewHolder {

        private TextView judul, lokasi;
        private CircleImageView mImageViewList;
        private RelativeLayout mItem;

        public ListHolder(View itemView) {
            super(itemView);

            judul = (TextView) itemView.findViewById(R.id.txtJudul);
            lokasi = (TextView) itemView.findViewById(R.id.txtLokasi);
            mImageViewList = (CircleImageView) itemView.findViewById(R.id.image);
            mItem = (RelativeLayout) itemView.findViewById(R.id.item);
        }
    }
}
