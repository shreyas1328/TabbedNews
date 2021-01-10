package com.shreyas.tabbednews.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.shreyas.tabbednews.R;
import com.shreyas.tabbednews.ViewModel.NewsSourceViewModel;
import com.shreyas.tabbednews.adapter.MyPagerAdapter;
import com.shreyas.tabbednews.model.SourceItemModel;
import com.shreyas.tabbednews.model.SourceModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;

    private NewsSourceViewModel newsSourceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        newsSourceViewModel = new ViewModelProvider(this).get(NewsSourceViewModel.class);
        newsSourceViewModel.getAllNewsSource().observe(this, new Observer<SourceModel>() {
            @Override
            public void onChanged(SourceModel sourceModel) {
                Log.d("main_ac_my", "onChanged: " + sourceModel);

                setTabLayout(sourceModel);
            }
        });


    }

    private void setTabLayout(SourceModel sourceModel) {
        if (sourceModel != null) {
            Log.d("my_tabcount", "sourceModel: " + sourceModel.getSources().size());

            for (int l = 0; l < sourceModel.getSources().size(); l++) {
                tabLayout.addTab(tabLayout.newTab().setText(sourceModel.getSources().get(l).getName()));
//                tabApiUrl.add(parseUrlToApi(sourceModel.getSources().get(l).getUrl()));
            }


            Log.d("my_tabcount", "tabLayout: " +  tabLayout.getTabCount());
            pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), sourceModel.getSources());
            viewPager.setAdapter(pagerAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            if (tabLayout.getTabCount() == 2) {
                tabLayout.setTabMode(TabLayout.MODE_FIXED);
            } else {
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }

        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
