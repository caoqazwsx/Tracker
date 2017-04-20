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
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
       mvMap.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mvMap.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mvMap.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mvMap.onSaveInstanceState(outState);
    }
}
