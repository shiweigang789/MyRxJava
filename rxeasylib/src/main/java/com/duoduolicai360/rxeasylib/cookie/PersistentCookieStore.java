package com.duoduolicai360.rxeasylib.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.duoduolicai360.rxeasylib.utils.HttpLog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by swg on 2017/9/7.
 */

public class PersistentCookieStore {

    private static final String COOKIE_PREFS = "Cookies_Preds";

    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies;
    private final SharedPreferences cookiePrefs;

    public PersistentCookieStore(Context context){
        cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        cookies = new HashMap<>();
        Map<String, ?> prefsMap = cookiePrefs.getAll();

        for (Map.Entry<String, ?> entry : prefsMap.entrySet()){
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
            for (String name : cookieNames) {
                String encodeCookie = cookiePrefs.getString(name, null);
                if (encodeCookie != null){
                    Cookie decodeCookie = decodeCookie(encodeCookie);
                    if (decodeCookie != null){
                        if (!cookies.containsKey(entry.getKey())){
                            cookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }
                        cookies.get(entry.getKey()).put(name, decodeCookie);
                    }
                }
            }
        }

    }

    protected String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(HttpUrl url, Cookie cookie){
        String name = getCookieToken(cookie);
        if (cookie.persistent()){
            if (!cookies.containsKey(url.host())){
                cookies.put(url.host(), new ConcurrentHashMap<String, Cookie>());
            }
            cookies.get(url.host()).put(name, cookie);
            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
            prefsWriter.putString(url.host(), TextUtils.join(",", cookies.get(url.host()).keySet()));
            prefsWriter.putString(name, encodeCookie(new SerializableOkHttpCookies(cookie)));
            prefsWriter.apply();
        } else {
            if (cookies.containsKey(url.host())){
                cookies.get(url.host()).remove(name);
            }
        }
    }

    public void addCookies(List<Cookie> cookies){
        for (Cookie cookie : cookies){
            String domain = cookie.domain();
            ConcurrentHashMap<String, Cookie> domainCookies = this.cookies.get(domain);
            if (domainCookies == null){
                domainCookies = new ConcurrentHashMap<>();
                this.cookies.put(domain, domainCookies);
            }
            cookies.add(cookie);
        }
    }

    public List<Cookie> get(HttpUrl url){
        ArrayList<Cookie> ret = new ArrayList<>();
        if (cookies.containsKey(url.host())){
            ret.addAll(cookies.get(url.host()).values());
        }
        return ret;
    }

    public boolean removeAll(){
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        cookies.clear();
        return true;
    }

    public boolean remove(HttpUrl url, Cookie cookie){
        String name = getCookieToken(cookie);
        if (cookies.containsKey(url.host()) && cookies.get(url.host()).containsKey(name)){
            cookies.get(url.host()).remove(name);
            SharedPreferences.Editor prefswriter = cookiePrefs.edit();
            if (cookiePrefs.contains(name)){
                prefswriter.remove(name);
            }
            prefswriter.putString(url.host(), TextUtils.join(",", cookies.get(name).keySet()));
            prefswriter.apply();
            return true;
        } else {
            return false;
        }
    }

    public List<Cookie> getCookies(){
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : cookies.keySet()){
            ret.addAll(cookies.get(key).values());
        }
        return ret;
    }


    /**
     * cookies to string
     */
    protected String encodeCookie(SerializableOkHttpCookies cookie){
        if (cookie == null){
            return null;
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e) {
            HttpLog.d("IOException in encodeCookie" + e.getMessage());
            return null;
        }
        return byteArrayToHexString(os.toByteArray());
    }

    /**
     * String to cookies
     */
    protected Cookie decodeCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableOkHttpCookies) objectInputStream.readObject()).getCookies();
        } catch (IOException e) {
            HttpLog.d("IOException in decodeCookie" + e.getMessage());
        } catch (ClassNotFoundException e) {
            HttpLog.d("ClassNotFoundException in decodeCookie" + e.getMessage());
        }

        return cookie;
    }

    /**
     * byteArrayToHexString
     */
    protected String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /**
     * hexStringToByteArray
     */
    protected byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

}