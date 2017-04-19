package com.zhao.tracker.source.parser.impl;

import android.util.Xml;


import com.zhao.tracker.model.Weather;
import com.zhao.tracker.source.parser.WeatherParser;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by zhao on 2017/4/8.
 */

public class PullWeatherParser implements WeatherParser {

    @Override
    public Weather parse(InputStream in) throws Exception {
        Weather weather = new Weather();
        Class weaClass = weather.getClass();
        Field[] fs = weaClass.getDeclaredFields();

        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(in, "UTF-8");               //设置输入流 并指明编码方式
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:
                    for(int i = 0 ; i < fs.length; i++){
                        Field f = fs[i];
                        f.setAccessible(true); //设置些属性是可以访问的
                        if (parser.getName().equals(fs[i].getName())) {
                            eventType = parser.next();
                            f.set(weather,parser.getText());
                            break;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:

                    break;
            }
            eventType = parser.next();
        }
        return weather;
    }

    @Override
    public String serialize(Weather books) throws Exception {
        return null;
    }
}
