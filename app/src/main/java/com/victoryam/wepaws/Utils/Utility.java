package com.victoryam.wepaws.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Domain.Clinic;
import com.victoryam.wepaws.Domain.Dining;
import com.victoryam.wepaws.Domain.Hotel;
import com.victoryam.wepaws.Domain.Park;
import com.victoryam.wepaws.Domain.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Utility {
    private Gson gson;

    public Utility() {
        gson = new Gson();
    }

    public List<Clinic> DeserializeClinic(String json) {
        List<Clinic> targetObject = gson.fromJson(json, new TypeToken<List<Clinic>>(){}.getType());
        return targetObject;
    }

    public List<Store> DeserializeStore(String json) {
        List<Store> targetObject = gson.fromJson(json, new TypeToken<List<Store>>(){}.getType());
        return targetObject;
    }

    public List<Dining> DeserializeDining(String json) {
        List<Dining> targetObject = gson.fromJson(json, new TypeToken<List<Dining>>(){}.getType());
        return targetObject;
    }

    public List<Park> DeserializePark(String json) {
        List<Park> targetObject = gson.fromJson(json, new TypeToken<List<Park>>(){}.getType());
        return targetObject;
    }

    public List<Park> DeserializeHotel(String json) {
        List<Park> targetObject = gson.fromJson(json, new TypeToken<List<Hotel>>(){}.getType());
        return targetObject;
    }

    public String SerializeObject(Object targetObject) {
        String jsonString = gson.toJson(targetObject);
        return jsonString;
    }

    public static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer("");
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException e) {
            Log.e("HttpGetTask", "IOException");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
}
