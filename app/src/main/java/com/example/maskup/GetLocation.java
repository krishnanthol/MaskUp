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

import io.paperdb.Paper;

public class GetLocation extends AsyncTask<Void, Void, Void>
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
            MainActivity.town = geocoderResults.getJSONObject(0).getString("name");
            MainActivity.state = geocoderResults.getJSONObject(0).getString("state");


            Log.d("county",MainActivity.county);

            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    if(MainActivity.county != null && MainActivity.state!= null)
                    {
                        Paper.book().write("county", MainActivity.county);
                        Paper.book().write("town",MainActivity.town);
                        Paper.book().write("state", MainActivity.state);
                        MainActivity.getLocation.cancel(true);
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
