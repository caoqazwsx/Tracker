package com.zhao.tracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.zhao.tracker.R;
import com.zhao.tracker.presenter.MainPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.tv_last_time)
    TextView tvLastTime;
    @InjectView(R.id.mv_map)
    MapView mvMap;

    private MainPresenter mMainPresenter;

    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        this.savedInstanceState = savedInstanceState;
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.start();

    }

    public TextView getTvLastTime() {
        return tvLastTime;
    }

    public MapView getMvMap() {
        return mvMap;
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();

       mMainPresenter.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
       mMainPresenter.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       mMainPresenter.onSaveInstanceState(outState);
    }
}
