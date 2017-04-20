package com.zhao.tracker.presenter;

import android.content.Intent;
import android.view.View;

import com.zhao.tracker.activity.LoginActivity;
import com.zhao.tracker.activity.MainActivity;
import com.zhao.tracker.callback.ResultCallback;
import com.zhao.tracker.common.APPCONST;
import com.zhao.tracker.util.StringHelper;
import com.zhao.tracker.util.TextHelper;
import com.zhao.tracker.webapi.CommonApi;

/**
 * Created by zhao on 2017/4/20.
 */

public class LoginPresenter implements BasePresenter {

    private LoginActivity mLoginActivity;
    private String key;

    public LoginPresenter(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    @Override
    public void start() {
        mLoginActivity.getBtnConfirm().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = mLoginActivity.getEtKey().getText().toString();
                if(StringHelper.isEmpty(key)){
                    TextHelper.showText("Key 不能为空");
                }else {
                    CommonApi.checkKey(key, new ResultCallback() {
                        @Override
                        public void onFinish(Object o, int code) {
                            if(code == 0){
                                APPCONST.KEY = key;
                                Intent intent = new Intent(mLoginActivity, MainActivity.class);
                                mLoginActivity.startActivity(intent);
                                mLoginActivity.finish();
                            }else {
                                TextHelper.showText("Key无效，请检查");
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            TextHelper.showText("服务器错误");
                        }
                    });
                }

            }
        });
    }


}
