package com.wepaws.wepaws.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Http_Get extends Service {

    private String path;

    public String Get(String url){
        this.path = url;
        final String[] data = new String[1];

        new Thread(new Runnable() {

            @Override
            public void run() {
                HttpURLConnection conn = null;
                InputStream inStream;

                try {
                    //Create a URL Connection object and set its parameters
                    URL url = new URL(path);
                    conn = (HttpURLConnection) url.openConnection();
                    //Set connection time out of 5 seconds
                    conn.setConnectTimeout(5000);
                    //Set read connection time out of 2.5 seconds
                    conn.setReadTimeout(2500);
                    //Set HTTP request method
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    //Perform network request
                    conn.connect();

                    //After the network response, retrieve the input stream
                    inStream = conn.getInputStream();
                    //Convert the input stream to String Bitmap
                    data[0] = readStream(inStream);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();

        return data[0];
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String readStream(InputStream in) {
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
