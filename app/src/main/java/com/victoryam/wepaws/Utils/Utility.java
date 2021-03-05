package com.victoryam.wepaws.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.victoryam.wepaws.Domain.Category;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utility {
    private Gson gson;

    public Utility() {
        gson = new Gson();
    }

//    public Animal DeserializeAnimal(String json) {
//        Animal targetObject = gson.fromJson(json, Animal.class);
//        return targetObject;
//    }
//
//    public Category DeserializeCategory(String json) {
//        Category targetObject = gson.fromJson(json, Category.class);
//        return targetObject;
//    }
//
//    public Vet DeserializeVet(String json) {
//        Vet targetObject = gson.fromJson(json, Vet.class);
//        return targetObject;
//    }

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
