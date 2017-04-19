package com.zhao.tracker.callback;


import com.zhao.tracker.model.JsonModel;

/**
 * Created by zhao on 2016/10/25.
 */

public interface JsonCallback {

    void onFinish(JsonModel jsonModel);


    void onError(Exception e);

}
