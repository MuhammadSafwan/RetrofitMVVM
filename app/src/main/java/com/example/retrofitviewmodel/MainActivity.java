package com.example.retrofitviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.retrofitviewmodel.adapter.CustomAdapter;
import com.example.retrofitviewmodel.model.RetroPhoto;
import com.example.retrofitviewmodel.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    List<RetroPhoto> photoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getPhotoRepsitory().observe(this, new Observer<List<RetroPhoto>>() {
            @Override
            public void onChanged(List<RetroPhoto> retroPhotos) {
                photoList.addAll(retroPhotos);
                mAdapter.notifyDataSetChanged();
            }
        });
        generateDataList();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void generateDataList() {
        mRecyclerView = findViewById(R.id.recycler_view);
        if(mAdapter == null) {
            mAdapter = new CustomAdapter(this, photoList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
