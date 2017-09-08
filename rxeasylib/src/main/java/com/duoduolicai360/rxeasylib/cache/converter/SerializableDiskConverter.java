package com.duoduolicai360.rxeasylib.cache.converter;

import com.duoduolicai360.rxeasylib.utils.HttpLog;
import com.duoduolicai360.rxeasylib.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * Created by swg on 2017/9/8.
 */

public class SerializableDiskConverter implements IDiskConverter {

    @Override
    public <T> T load(InputStream source, Type type) {
        T value = null;
        ObjectInputStream os = null;
        try {
            os = new ObjectInputStream(source);
            value = (T) os.readObject();
        } catch (IOException | ClassNotFoundException e){
            HttpLog.e(e);
        } finally {
            Utils.close(os);
        }

        return value;
    }

    @Override
    public boolean writer(OutputStream sink, Object data) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(sink);
            oos.writeObject(data);
            oos.flush();
            return true;
        } catch (IOException e) {
            HttpLog.e(e);
        } finally {
            Utils.close(oos);
        }
        return false;
    }

}
