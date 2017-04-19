package com.zhao.tracker.source.parser;



import com.zhao.tracker.model.Weather;

import java.io.InputStream;

/**
 * Created by zhao on 2017/4/8.
 */

public interface WeatherParser {
    /**
     * 解析输入流 得到Weather对象
     * @param is
     * @return
     * @throws Exception
     */
     Weather parse(InputStream is) throws Exception;

    /**
     * 序列化Weather对象 得到XML形式的字符串
     * @param books
     * @return
     * @throws Exception
     */
     String serialize(Weather books) throws Exception;
}
