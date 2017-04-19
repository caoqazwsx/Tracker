package com.zhao.tracker.common;

import android.os.Environment;


/**
 * Created by zhao on 2016/10/20.
 */

public class APPCONST {

    public static String KEY ;
    public static String DB_NAME = "track";
    public static String DESCRIPTION;
    public static String publicKey;
    public static String privateKey;

    public static String SYS_DATA_FILE_NAME = "sysData";







//    public static final String FILE_DIR = Environment.getExternalStorageDirectory() + "/gxdw";
    public static final String FILE_DIR = "gxdw";
    public static final String UPDATE_APK_FILE_DIR = "gxdw/apk/";
    public static final String TEM_FILE_DIR = Environment.getExternalStorageDirectory() + "/gxdw/tem/";

    public static final String QRCODE_FILE_NAME = "qrcode";


    public static final int REQUEST_MEETING_APPLY = 1001;
    public static final int REQUEST_MEETING_LEAVE = 1002;
    public static final int REQUEST_USER_APPLY = 1003;
    public static final int REQUEST_USER_LEAVE = 1004;
    public static final int REQUEST_ADD_SCHEDULE = 1005;
    public static final int REQUEST_EDIT_SCHEDULE = 1006;
    public static final int REQUEST_MEETING_INFO = 1007;

    public static final int RESULT_DELETE_SCHEDULE = 2001;

    public static final String key = "cnfia";
    public static final String cacheKey="CacheKlGxdw2016";
    public static final String httpKey ="HttpKlGxdw2016";



}
