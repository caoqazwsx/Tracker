package com.zhao.tracker.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import com.zhao.tracker.callback.ResultCallback;
import com.zhao.tracker.common.APPCONST;
import com.zhao.tracker.model.TrackEntity;
import com.zhao.tracker.receiver.AwakeUTLServiceReceiver;
import com.zhao.tracker.webapi.CommonApi;


public class UpdateTargetLocationService extends Service {


    private UpdateTargetLocationController updateTargetLocationController;
    private boolean runing;

    public UpdateTargetLocationService() {
        updateTargetLocationController = new UpdateTargetLocationController();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getTargetLocation();
        awake(2000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return updateTargetLocationController;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    private void getTargetLocation() {
        CommonApi.getTargetLocation(APPCONST.KEY, new ResultCallback() {

            @Override
            public void onFinish(Object o, int code) {
                if (updateTargetLocationController.onTargetLocationChangeListener != null) {
                    updateTargetLocationController.onTargetLocationChangeListener.onLocation((TrackEntity) o);
                }
            }

            @Override
            public void onError(Exception e) {

            }

        });
    }


    private void awake(int milli) {
        if (runing) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent it = new Intent(this, AwakeUTLServiceReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(this, 1, it, PendingIntent.FLAG_CANCEL_CURRENT);
            long triggerAtTime = SystemClock.elapsedRealtime() + milli;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,triggerAtTime,pi);
            }else {
                alarmManager.set(AlarmManager.RTC_WAKEUP,triggerAtTime,pi);
            }
        }
    }

    public class UpdateTargetLocationController extends Binder {

        protected OnTargetLocationChangeListener onTargetLocationChangeListener;

        public void setOnTargetLocationChangeListener(OnTargetLocationChangeListener onTargetLocationChangeListener) {
            this.onTargetLocationChangeListener = onTargetLocationChangeListener;
        }

        public void stop() {
            runing = false;
        }

        public void start() {
            runing = true;
        }

    }

    public interface OnTargetLocationChangeListener {

        void onLocation(TrackEntity trackEntity);
    }


}
