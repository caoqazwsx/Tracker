package com.zhao.tracker.application;

import android.app.Application;
import android.content.Context;


/**
 * Created by zhao on 2017/4/19.
 */

public class MyApplication extends Application {

    private static Application application;




    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }

    public static Context getContext(){
        return application;
    }

    public static Application getApplication() {
        return application;
    }
}
