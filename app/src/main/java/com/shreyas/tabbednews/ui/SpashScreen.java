package com.shreyas.tabbednews.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shreyas.tabbednews.R;
import com.shreyas.tabbednews.ViewModel.NewsSourceViewModel;
import com.shreyas.tabbednews.model.SourceModel;
import com.shreyas.tabbednews.roomDb.MyCallBack;

public class SpashScreen extends AppCompatActivity {

    private NewsSourceViewModel newsSourceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        newsSourceViewModel = new ViewModelProvider(this).get(NewsSourceViewModel.class);
//        newsSourceViewModel.getAllNewsSource().observe(this, new Observer<SourceModel>() {
//            @Override
//            public void onChanged(SourceModel sourceModel) {
//                Log.d("qwe_tyu", "onChanged: "+sourceModel);
//            }
//        });
        newsSourceViewModel.getNewsSourceFromApi().observe(this, new Observer<SourceModel>() {
            @Override
            public void onChanged(SourceModel sourceModel) {
                Log.d("qwe_tyu", "onChanged: "+sourceModel);
                Intent intent = new Intent(SpashScreen.this, MainActivity.class);
                SpashScreen.this.startActivity(intent);
            }
        });

    }
}
