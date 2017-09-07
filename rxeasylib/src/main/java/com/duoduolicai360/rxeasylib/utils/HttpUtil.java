package com.duoduolicai360.rxeasylib.utils;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by swg on 2017/9/7.
 */

public class HttpUtil {

    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static String createUrlFromParams(String url, Map<String, String> params){
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            if (url.indexOf("&") > 0 || url.indexOf("?") > 0){
                sb.append("&");
            } else {
                sb.append("?");
            }

            for (Map.Entry<String, String> entry : params.entrySet()){
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (Exception e){
            HttpLog.e(e.getMessage());
        }
        return url;
    }

}
