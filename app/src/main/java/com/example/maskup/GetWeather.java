package com.example.maskup;

import android.os.AsyncTask;
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
import java.text.SimpleDateFormat;

public class GetWeather extends AsyncTask<Void, Void, Void>
{
    private String coordinatesString = "";
    private String weatherString = "";

    private String latitude;
    private String longitude;

    private double temps;

    @Override
    protected Void doInBackground(Void...voids)
    {
        try
        {
            URL url = new URL("https://api.openweathermap.org/geo/1.0/zip?zip=" + MainActivity.zipCode + ",US&appid=eb140fb4e55477652cd7b6f69d0d1c9f");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();

            while (line != null)
            {
                coordinatesString += line;
                line = br.readLine();
            }

            JSONObject coordinates = new JSONObject(coordinatesString);
            latitude = coordinates.getString("lat");
            longitude = coordinates.getString("lon");


            URL url1 = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&units=imperial&appid=eb140fb4e55477652cd7b6f69d0d1c9f");
            URLConnection urlConnection1 = url1.openConnection();
            InputStream inputStream1 = urlConnection1.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(inputStream1));
            String line1 = br1.readLine();

            while (line1 != null)
            {
                weatherString += line1;
                line1 = br.readLine();
            }

            JSONObject weatherAll = new JSONObject(weatherString);
            JSONArray hourlyForecast = new JSONArray(weatherAll.getString("hourly"));

            for (int i = 0; i < 5; i++)
            {
                SimpleDateFormat jdf=new SimpleDateFormat("hh:mm a");
                java.util.Date time=new java.util.Date((long)Integer.parseInt(hourlyForecast.getJSONObject(i).getString("dt"))*1000);
                String hour=jdf.format(time);

                String temp=hourlyForecast.getJSONObject(i).getString("temp");

                JSONArray weather=new JSONArray(hourlyForecast.getJSONObject(i).getString("weather"));

                JSONObject idk=new JSONObject(weather.getString(0));
                String image=idk.getString("icon");

                String description=idk.getString("description");
                Log.d("description",description);

                Weather forecast=new Weather(hour,temp,image,description);

                MainActivity.forecasts.add(forecast);
            }

            for (int i = 0; i < 10; i++)
            {
                temps+=Double.parseDouble(hourlyForecast.getJSONObject(i).getString("temp"));
            }

            MainActivity.averageTemp = temps/10;
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
