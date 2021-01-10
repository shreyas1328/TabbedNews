package com.shreyas.tabbednews.utils;

import android.util.Log;

public class Utils {

    public static final String API_KEY = "ae0d1be2cf604bba9c07e94869991dac";

    public static String parseUrlToApi(String url) {
        if (url != "" || url != null) {
            Log.d("parseUrlToApi", "parseUrlToApi: "+url);
            return url.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)", "");
        } else {
            return "";
        }
    }
}
