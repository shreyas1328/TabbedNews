package com.shreyas.tabbednews.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyas.tabbednews.R;
import com.shreyas.tabbednews.ViewModel.NewsViewModel;
import com.shreyas.tabbednews.adapter.NewsRecyclerAdapter;
import com.shreyas.tabbednews.data.DummyNews;
import com.shreyas.tabbednews.model.NewsList;
import com.shreyas.tabbednews.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class DynamicFragment extends Fragment {

    private View view;
    private RecyclerView evFrag;
    private TextView tvNo, mTvNoNews;
    private String val;
    private RelativeLayout rl;
    private RecyclerView mRvNews;
    private NewsRecyclerAdapter newsRecyclerAdapter;

    private NewsViewModel newsViewModel;
    private List<NewsModel> newsModels;

    public static DynamicFragment newInstance(String val) {
        Log.d("my_index", "newInstance: " + val);
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString("apiValue", val);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dynamic_fragment, container, false);
        mRvNews = view.findViewById(R.id.news_rv);
        mTvNoNews = view.findViewById(R.id.tv_noNews);
        rl = view.findViewById(R.id.rl);
        mRvNews = view.findViewById(R.id.news_rv);

        newsModels = new ArrayList<>();

        newsRecyclerAdapter = new NewsRecyclerAdapter(getActivity(), newsModels);
        mRvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvNews.setAdapter(newsRecyclerAdapter);

        val = getArguments().getString("apiValue");
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.getNewsFromApi(val).observe(getViewLifecycleOwner(), new Observer<NewsList>() {
            @Override
            public void onChanged(NewsList newsList) {
                Log.d("news_list", "onChanged: " + newsList.getArticles());
                if (newsList.getArticles().size() != 0) {
                    mRvNews.setVisibility(View.VISIBLE);
                    mTvNoNews.setVisibility(View.GONE);
                } else {
                    mRvNews.setVisibility(View.INVISIBLE);
                    mTvNoNews.setVisibility(View.VISIBLE);
                }
                newsRecyclerAdapter.setNewsArray(newsList.getArticles());
                newsRecyclerAdapter.notifyDataSetChanged();
            }
        });


        return view;
    }
}
