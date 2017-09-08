package com.duoduolicai360.rxeasylib.cache.converter;

import com.duoduolicai360.rxeasylib.utils.HttpLog;
import com.duoduolicai360.rxeasylib.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * Created by swg on 2017/9/8.
 */

public class GsonDiskConverter implements IDiskConverter {

    private Gson gson;

    public GsonDiskConverter() {
        this.gson = new Gson();
    }

    public GsonDiskConverter(Gson gson) {
        Utils.checkNotNull(gson, "gson ==null");
        this.gson = gson;
    }

    @Override
    public <T> T load(InputStream source, Type type) {
        T value = null;
        try {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            JsonReader jsonReader = gson.newJsonReader(new InputStreamReader(source));
            value = (T) adapter.read(jsonReader);
        } catch (JsonIOException | IOException | JsonSyntaxException e){
            HttpLog.e(e.getMessage());
        } finally {
            Utils.close(source);
        }
        return value;
    }

    @Override
    public boolean writer(OutputStream sink, Object data) {
        try {
            String json = gson.toJson(data);
            byte[] bytes = json.getBytes();
            sink.write(bytes, 0, bytes.length);
            sink.flush();
            return true;
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            HttpLog.e(e.getMessage());
        } finally {
            Utils.close(sink);
        }
        return false;
    }
}
