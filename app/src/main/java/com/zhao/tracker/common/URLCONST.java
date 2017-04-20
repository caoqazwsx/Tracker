package com.zhao.tracker.common;

/**
 * Created by zhao on 2016/10/20.
 */

public class URLCONST {

    // 命名空间
//    public static final String nameSpace = "http://dwoa.gxi.gov.cn";


    //      public static final String nameSpace = "http://10.1.15.45:80";
     public static final String nameSpace = "http://10.10.123.31:8080/jeecg";
//    public static final String nameSpace = "http://10.10.123.221:8080/contacts";
//    public static final String nameSpace = "http://10.10.123.116:8899/contacts";
    public static boolean isRSA = true;

    //天气获取
    public static final String method_weather = " http://php.weather.sina.com.cn/xml.php?";

    //获取目标位置
    public static final String method_getTargetLocation = nameSpace + "/mTrackController.do?getTargetLocation";

    //验证Key的有效性
    public static final String method_checkKey = nameSpace + "/mTrackController.do?checkKey";


}
