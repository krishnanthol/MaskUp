package com.example.maskup;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetStats extends AsyncTask<Void, Void, Void>
{
    private String statsString = "";
    private int statsObject;
    private String[] statesAll = {"Alabama", "Alaska", "Arizona", "Arkansas","California", "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire","New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
    private String[] abbrevAll = {"AL","AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {

            for(int i = 0; i < statesAll.length; i++)
            {
                if(MainActivity.state.equals(statesAll[i]))
                {
                    MainActivity.state = abbrevAll[i];
                }
            }

            Log.d("state",MainActivity.state);

            URL url = new URL("https://api.covidactnow.org/v2/county/"+ MainActivity.state +".json?apiKey=eba19fd5e59748e08ed39bb96253239c");
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
            for (int i = 0; i < statsAll.length(); i++)
            {
                if(MainActivity.county.equals(statsAll.getJSONObject(i).getString("county")))
                {
                    statsObject = i;
                    i = statsAll.length();
                    Log.d("statsObject",""+statsObject);
                }
            }

            MainActivity.covidCases = statsAll.getJSONObject(statsObject).getJSONObject("actuals").getString("newCases");
            Log.d("stats",MainActivity.covidCases);
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
