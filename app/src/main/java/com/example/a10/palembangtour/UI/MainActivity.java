package com.example.a10.palembangtour.UI;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.a10.palembangtour.Models.Adapter.AdapterSlider;
import com.example.a10.palembangtour.Models.Adapter.MenuAdapter;
import com.example.a10.palembangtour.Models.MenuModel;
import com.example.a10.palembangtour.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;
    private List<MenuModel> menuList;
    private ViewPager viewPager;
    private static int currentPage = 0;

    private static final int[] sampleImages = {
            R.drawable.masjid_agung,
            R.drawable.burgo,
            R.drawable.benteng_kuto_besak,
            R.drawable.makam_ki_gede,
            R.drawable.sportcity};
    private ArrayList<Integer> imageAdd = new ArrayList<Integer>();
    private AdapterSlider adapterSlider;

    private Toast toast;
    private long lastBackPressTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        initView();

        configView();

        listMenu();
    }

    private void initView(){
        for (int i= 0; i<sampleImages.length; i++ ){
            imageAdd.add(sampleImages[i]);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapterSlider = new AdapterSlider(MainActivity.this, imageAdd);
        viewPager.setAdapter(adapterSlider);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == sampleImages.length){
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 7000, 7000);
    }

    private void listMenu() {

        MenuModel data = new MenuModel(getString(R.string.wstAlam), R.drawable.alam);
        menuList.add(data);
        data = new MenuModel(getString(R.string.wstSajarahBudaya), R.drawable.budaya);
        menuList.add(data);
        data = new MenuModel(getString(R.string.wstBuatan), R.drawable.buatan);
        menuList.add(data);
        data = new MenuModel(getString(R.string.wstMakamSultan), R.drawable.makam);
        menuList.add(data);
        data = new MenuModel(getString(R.string.wstKuliner), R.drawable.kuliner);
        menuList.add(data);
    }

    private void configView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        menuList = new ArrayList<>();
        mAdapter = new MenuAdapter(MainActivity.this,menuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            Toast.makeText(this, "Press back again to close this app", Toast.LENGTH_SHORT).show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activitymain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_about:
                Toast.makeText(this, "Informasi App", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_info:
                Toast.makeText(this, "Tentang App", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public void onClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getApplicationContext(), ListActivity.class)
                        .putExtra("title", getString(R.string.wstSajarahBudaya)));
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(), ListActivity.class)
                        .putExtra("title", getString(R.string.wstKuliner)));
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), ListActivity.class)
                        .putExtra("title", getString(R.string.wstAlam)));
                break;
            case 3:
                startActivity(new Intent(getApplicationContext(), ListActivity.class)
                        .putExtra("title", getString(R.string.wstMakamSultan)));
                break;
            case 4:
                startActivity(new Intent(getApplicationContext(), ListActivity.class)
                        .putExtra("title", getString(R.string.wstBuatan)));
                break;
        }
    }*/
}
