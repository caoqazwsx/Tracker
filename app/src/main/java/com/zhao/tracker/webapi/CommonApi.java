package com.zhao.tracker.webapi;


import com.google.gson.Gson;
import com.zhao.tracker.callback.JsonCallback;
import com.zhao.tracker.callback.ResultCallback;
import com.zhao.tracker.callback.WeatherCallback;
import com.zhao.tracker.common.APPCONST;
import com.zhao.tracker.common.URLCONST;
import com.zhao.tracker.model.JsonModel;
import com.zhao.tracker.model.TrackEntity;
import com.zhao.tracker.model.Weather;
import com.zhao.tracker.source.HttpDataSource;
import com.zhao.tracker.util.HttpUtil;
import com.zhao.tracker.util.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhao on 2017/4/19.
 */

public class CommonApi {

    /**
     * 获取天气信息
     * @param city
     * @param day
     * @param callback
     */
    public static void getWeather(String city, int day, final ResultCallback callback){
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("city",java.net.URLEncoder.encode(city.replace("市",""), "gb2312"));
            params.put("password","DJOYnieT8234jlsK");
            params.put("day",day);
            HttpDataSource.httpGetNoRSAByWeather(HttpUtil.makeURLNoRSA(URLCONST.method_weather,params),  new WeatherCallback() {
                @Override
                public void onFinish(Weather weather) {
                    callback.onFinish(weather,0);
                }

                @Override
                public void onError(Exception e) {
                    callback.onError(e);

                }
            });
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(e);
        }
    }

    /**
     * 获取目标位置
     * @param key
     * @param callback
     */
    public static void getTargetLocation(String key, final ResultCallback callback){
        Map<String,Object> params = new HashMap<>();
        params.put("key", key);
        HttpDataSource.httpGetNoRSA(HttpUtil.makeURLNoRSA(URLCONST.method_getTargetLocation, params), new JsonCallback() {
            @Override
            public void onFinish(JsonModel jsonModel) {
                try {
                    callback.onFinish(new Gson().fromJson(jsonModel.getResult(), TrackEntity.class),jsonModel.getError());
                }catch (Exception e){
                    callback.onError(e);
                }
            }
            @Override
            public void onError(Exception e) {
                callback.onError(e);

            }
        });

    }

    /**
     * 上传位置信息
     * @param key
     */
    public static void checkKey(String key,final ResultCallback callback) {
        Map<String, Object> params = new HashMap<>();
        if (!StringHelper.isEmpty(key)) {
            params.put("key", key);
        }

        HttpDataSource.httpGetNoRSA(HttpUtil.makeURLNoRSA(URLCONST.method_checkKey, params), new JsonCallback() {
            @Override
            public void onFinish(JsonModel jsonModel) {
                callback.onFinish(jsonModel.getResult(), jsonModel.getError());
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);

            }
        });
    }


}
