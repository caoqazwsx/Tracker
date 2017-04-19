package com.zhao.tracker.callback;


import com.zhao.tracker.model.Weather;

/**
 * Created by zhao on 2016/10/25.
 */

public interface WeatherCallback {

    void onFinish(Weather weather);

    void onError(Exception e);

}
