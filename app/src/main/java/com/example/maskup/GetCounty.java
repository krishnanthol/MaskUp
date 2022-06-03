package com.example.maskup;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetCounty extends AsyncTask<Void, Void, Void>
{
    private String geocoderString = "";

    @Override
    protected Void doInBackground(Void...voids)
    {
        try
        {
            URL url = new URL("https://api.geoapify.com/v1/geocode/search?text="+ MainActivity.zipCode +"&format=json&apiKey=a441e89ce9cf4d15b174eda7eec5beaf");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();

            while (line != null)
            {
                geocoderString += line;
                line = br.readLine();
            }

            JSONObject geocoderAll = new JSONObject(geocoderString);
            JSONArray geocoderResults = new JSONArray(geocoderAll.getString("results"));
            MainActivity.county = geocoderResults.getJSONObject(0).getString("county");
            MainActivity.state = geocoderResults.getJSONObject(0).getString("state");

            Log.d("county",MainActivity.county);

            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    if(MainActivity.county != null && MainActivity.state!= null)
                    {
                        for(int i = 0; i < MainActivity.county.length(); i++)
                        {
                            if(MainActivity.county.charAt(i) == ' ')
                            {
                                MainActivity.county = MainActivity.county.substring(0,i) + "%20" + MainActivity.county.substring(i+1,MainActivity.county.length());
                                i = 0;
                            }
                        }

                        Log.d("county",MainActivity.county);

                        MainActivity.getStats.execute();
                        MainActivity.getCounty.cancel(true);
                    }
                }
            });
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
