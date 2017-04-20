package com.zhao.tracker.presenter;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;


import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.zhao.tracker.R;
import com.zhao.tracker.activity.MainActivity;
import com.zhao.tracker.callback.ResultCallback;
import com.zhao.tracker.common.APPCONST;
import com.zhao.tracker.model.TrackEntity;
import com.zhao.tracker.util.DateHelper;
import com.zhao.tracker.webapi.CommonApi;

import org.json.JSONArray;


/**
 * Created by zhao on 2017/4/19.
 */

public class MainPresenter implements BasePresenter {
    private MainActivity mMainActivity;



    private AMap aMap;
    private TrackEntity mTrackEntity;

    private Marker marker;



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    try {
                        JSONArray jsonArray = new JSONArray(mTrackEntity.getLocation());
                        marker(jsonArray.getDouble(0), jsonArray.getDouble(1));
                        mMainActivity.getTvLastTime().setText(DateHelper.longToTime(mTrackEntity.getLastTime()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    public MainPresenter(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void start() {
        init();
        getTargetLocation();
    }

    private void init() {
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMainActivity.getMvMap().onCreate(mMainActivity.getSavedInstanceState());
        //初始化地图控制器对象
        aMap = mMainActivity.getMvMap().getMap();
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    private void marker(double lat, double lng) {
        LatLng latLng = new LatLng(lat, lng);
        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.position(XIAN);
//        markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");

//        markerOption.draggable(true);//设置Marker可拖动
        markerOption.position(latLng);
       /* markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mMainActivity.getResources(), R.drawable.red_circle)));*/
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        if (marker != null) {
            marker.destroy();
        }
        marker = aMap.addMarker(markerOption);

    }

    private void getTargetLocation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    CommonApi.getTargetLocation("4028b8815b855f4e015b8589f3fc0002", new ResultCallback() {
                        @Override
                        public void onFinish(Object o, int code) {
                            mTrackEntity = (TrackEntity) o;
                            handler.sendMessage(handler.obtainMessage(1));
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }


}
