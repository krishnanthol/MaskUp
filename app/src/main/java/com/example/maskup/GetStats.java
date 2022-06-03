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

public class GetStats extends AsyncTask<Void, Void, Void>
{
    private String statsString = "";

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {
            URL url = new URL("https://knowi.com/api/data/ipE4xJhLBkn8H8jisFisAdHKvepFR5I4bGzRySZ2aaXlJgie?entityName=Latest%20Day%20County%20Level%20Data&exportFormat=json&c9SqlFilter=select%20*%20where%20County%20like%20"+ MainActivity.county);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();

            while (line != null)
            {
                statsString += line;
                line = br.readLine();
            }

            JSONArray statsAll = new JSONArray(statsString);

            for(int i = 0; i < statsAll.length(); i++)
            {
                if((statsAll.getJSONObject(i).getString("State").equals(MainActivity.state)) && (statsAll.getJSONObject(i).getString("Type").equals("Confirmed")))
                {
                    MainActivity.covidCases = statsAll.getJSONObject(i).getString("values");
                    Log.d("covidCases",MainActivity.covidCases);
                    i = statsAll.length();
                }
            }
        }

        catch(IOException e)
        {

        }

        catch(JSONException e)
        {

        }

        return null;
    }
}
