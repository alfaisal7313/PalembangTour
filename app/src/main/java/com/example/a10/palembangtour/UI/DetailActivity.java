package com.example.a10.palembangtour.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.a10.palembangtour.R;

public class DetailActivity extends AppCompatActivity {

    private TextView dayaTarik, fasilitas, pengelola;
    private FloatingActionButton onMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        controlScreen();

    }

    private void controlScreen() {

        Intent data = getIntent();
        String intTitle = data.getStringExtra("title");
        String intImage = data.getStringExtra("images");
        String intDayaTarik = data.getStringExtra("dayaTarik");
        String intFasilitas = data.getStringExtra("fasilitas");
        String intPengelola = data.getStringExtra("pengelola");

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(intTitle);
        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);

        Glide.with(DetailActivity.this).load(intImage).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(resource);
                    }
                });

        Log.d("Res Image", String.valueOf(intImage));

        onMap = (FloatingActionButton) findViewById(R.id.onMap);
        dayaTarik = (TextView) findViewById(R.id.dayaTarik);
        fasilitas = (TextView) findViewById(R.id.fasilitas);
        pengelola = (TextView) findViewById(R.id.pengelola);

        dayaTarik.setText(intDayaTarik);
        fasilitas.setText(intFasilitas);
        pengelola.setText(intPengelola);

        onMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MapsActivity.class));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
