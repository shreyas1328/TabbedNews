package com.shreyas.tabbednews.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.shreyas.tabbednews.fragment.DynamicFragment;
import com.shreyas.tabbednews.model.SourceItemModel;
import com.shreyas.tabbednews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    List<SourceItemModel> tabSourceModel;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs, List<SourceItemModel> tabSourceModel) {
        super(fm, NumOfTabs);
        this.mNumOfTabs = NumOfTabs;
        this.tabSourceModel = tabSourceModel;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("get_getPageTitle", "getPageTitle: "+position+" "+tabSourceModel.get(position).getName());
        return tabSourceModel.get(position).getName();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DynamicFragment.newInstance(Utils.parseUrlToApi(tabSourceModel.get(position).getUrl()));
    }

    @Override
    public int getCount() {
        Log.d("my numbers ", "count " + mNumOfTabs);
        return tabSourceModel.size();
    }
}
