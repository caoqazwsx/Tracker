package com.zhao.tracker.source;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;


import com.google.gson.Gson;
import com.zhao.tracker.callback.HttpCallback;
import com.zhao.tracker.callback.JsonCallback;
import com.zhao.tracker.callback.WeatherCallback;
import com.zhao.tracker.common.APPCONST;
import com.zhao.tracker.common.URLCONST;
import com.zhao.tracker.model.JsonModel;
import com.zhao.tracker.model.Weather;
import com.zhao.tracker.source.parser.impl.PullWeatherParser;
import com.zhao.tracker.util.HttpUtil;
import com.zhao.tracker.util.RSAUtilV2;
import com.zhao.tracker.util.StringHelper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by zhao on 2016/4/16.
 */

public class HttpDataSource {

    public static void  httpGet(String url, final JsonCallback callback){
       Log.d("HttpGet URl", url);
       HttpUtil.sendGetRequest_okHttp(url, new HttpCallback() {
            @Override
            public void onFinish(Bitmap bm){

            }
            @Override
            public void onFinish(InputStream in){
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        response.append(line);
                        line = reader.readLine();
                    }
                    if(callback != null) {
                        Log.i("Http", "read finish："+response.toString());
                       // setResponse(response.toString());
                        JsonModel jsonModel = new Gson().fromJson(response.toString(), JsonModel.class);
//                        jsonModel.setResult(jsonModel.getResult().replace("\n",""));
//                        test(jsonModel.getResult());
//                        String str = new String(RSAUtilV2.decryptByPrivateKey(Base64.decode(jsonModel.getResult().replace("\n",""),Base64.DEFAULT),APPCONST.privateKey));
                        if(URLCONST.isRSA && !StringHelper.isEmpty(jsonModel.getResult())) {
                            jsonModel.setResult(StringHelper.decode(new String(RSAUtilV2.decryptByPrivateKey(Base64.decode(jsonModel.getResult().replace("\n", ""), Base64.DEFAULT), APPCONST.privateKey))));
                        }
                        callback.onFinish(jsonModel);
                        Log.d("Http", "RSA finish："+ new Gson().toJson(jsonModel));
                    }
                }catch (Exception e){
                    callback.onError(e);
                }
            }
            @Override
            public void onFinish(String response) {

            }

            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }

        });
    }

    public static void httpPost(String url, String output, final JsonCallback callback){
        Log.d("HttpPost:", url + "&" + output);
        HttpUtil.sendPostRequest_okHttp(url,output,new HttpCallback() {
            @Override
            public void onFinish(Bitmap bm){

            }
            @Override
            public void onFinish(InputStream in){
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        response.append(line);
                        line = reader.readLine();
                    }
                    if(callback != null) {
                        Log.d("Http", "read finish："+response);
                        // setResponse(response.toString());
                        JsonModel jsonModel = new Gson().fromJson(response.toString(), JsonModel.class);
                        if(URLCONST.isRSA && !StringHelper.isEmpty(jsonModel.getResult())) {
                            jsonModel.setResult(StringHelper.decode(new String(RSAUtilV2.decryptByPrivateKey(Base64.decode(jsonModel.getResult().replace("\n", ""), Base64.DEFAULT), APPCONST.privateKey))));
                        }
                        callback.onFinish(jsonModel);
                        Log.d("Http", "RSA finish："+ new Gson().toJson(jsonModel));
                    }
                }catch (Exception e){
                    callback.onError(e);
                }
            }
            @Override
            public void onFinish(String response) {
                Log.e("http",response);
            }
            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }

    public static void httpGetNoRSA(String url, final JsonCallback callback){
        Log.d("HttpGet URl", url);
        HttpUtil.sendGetRequest_okHttp(url, new HttpCallback() {
            @Override
            public void onFinish(Bitmap bm){

            }
            @Override
            public void onFinish(InputStream in){
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        response.append(line);
                        line = reader.readLine();
                    }
                    if(callback != null) {
                        Log.i("Http", "read finish："+response.toString());
                        // setResponse(response.toString());
                        JsonModel jsonModel = new Gson().fromJson(response.toString(), JsonModel.class);
                        callback.onFinish(jsonModel);
                        Log.d("Http", "RSA finish："+ new Gson().toJson(jsonModel));
                    }
                }catch (Exception e){
                    callback.onError(e);
                }
            }
            @Override
            public void onFinish(String response) {

            }
            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }



    public static <T> void httpGetNoRSAByWeather(String url,final WeatherCallback callback){
        Log.d("HttpGet URl", url);
        HttpUtil.sendGetRequest_okHttp(url, new HttpCallback() {
            @Override
            public void onFinish(Bitmap bm){

            }
            @Override
            public void onFinish(InputStream in){
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        response.append(line);
                        line = reader.readLine();
                    }
                    if(callback != null) {
                        Log.i("Http", "read finish："+response.toString());
                        // setResponse(response.toString());
                        Weather weather = new PullWeatherParser().parse(new ByteArrayInputStream(response.toString().getBytes("UTF-8")));
                        callback.onFinish(weather);
                        Log.d("Http", "RSA finish："+ new Gson().toJson(weather));
                    }
                }catch (Exception e){
                    callback.onError(e);
                }
            }
            @Override
            public void onFinish(String response) {

            }
            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }



    public static void httpGetBitmap(String url, final HttpCallback callback){
        Log.d("Http", "success1");
        HttpUtil.sendBitmapGetRequest(url, new HttpCallback() {
            @Override
            public void onFinish(Bitmap bm){

            }
            @Override
            public void onFinish(InputStream in){
                if(callback != null) {
                    Bitmap bm = BitmapFactory.decodeStream(in);
//                    setBitmap(bm);
                    callback.onFinish(bm);
                }
            }
            @Override
            public void onFinish(String response) {

            }
            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }

    public static void uploadFile(String url, String[] files, Map<String, Object> params, final JsonCallback callback){
        HttpUtil.uploadFileRequest(url,files,params,callback);
    }

    public static void httpGetFile(String url, final HttpCallback callback){
        Log.d("Http", "success1");
        HttpUtil.sendGetRequest(url, new HttpCallback() {
            @Override
            public void onFinish(Bitmap bm){

            }
            @Override
            public void onFinish(InputStream in){
                callback.onFinish(in);

            }
            @Override
            public void onFinish(String response) {

            }
            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }

    public static String makeURL(String p_url, Map<String,Object> params) {
      return HttpUtil.makeURL(p_url,params);
    }

    public static String makeURLNoRSA(String p_url, Map<String,Object> params) {
     return HttpUtil.makeURLNoRSA(p_url,params);
    }

    public static String makePostOutput(Map<String, Object> params) {
       return HttpUtil.makePostOutput(params);
    }

}
