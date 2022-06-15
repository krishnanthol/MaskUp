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

            URL url = new URL("https://api.covidactnow.org/v2/county/" + MainActivity.state + ".timeseries.json?apiKey=eba19fd5e59748e08ed39bb96253239c");
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
                }
            }

            for(int i = statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").length()-2; i > statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").length()-7; i--)
            {
                if(statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").getJSONObject(i).getString("newCases").equals("null"))
                {
                    MainActivity.countyNewCases.add(0);
                    //latest to oldest
                }
                else
                {
                    MainActivity.countyNewCases.add(Integer.parseInt(statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").getJSONObject(i).getString("newCases")));
                }
            }

            for(int i = statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").length()-2; i > statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").length()-7; i--)
            {
                if(statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").getJSONObject(i).getString("newDeaths").equals("null"))
                {
                    MainActivity.countyNewDeaths.add(0);
                    //latest to oldest
                }
                else
                {
                    MainActivity.countyNewDeaths.add(Integer.parseInt(statsAll.getJSONObject(statsObject).getJSONArray("actualsTimeseries").getJSONObject(i).getString("newDeaths")));
                }
            }

            MainActivity.countyRiskLevel = Integer.parseInt(statsAll.getJSONObject(statsObject).getJSONObject("riskLevels").getString("overall"));
            Log.d("riskLevel",""+MainActivity.countyRiskLevel);
            Log.d("stats",MainActivity.countyNewCases.toString());
            Log.d("stats",MainActivity.countyNewDeaths.toString());

            statsString = "";

            URL url1 = new URL("https://api.covidactnow.org/v2/country/US.timeseries.json?apiKey=eba19fd5e59748e08ed39bb96253239c");
            URLConnection urlConnection1 = url1.openConnection();
            InputStream inputStream1 = urlConnection1.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(inputStream1));
            String line1 = br1.readLine();

            while (line1 != null)
            {
                statsString += line1;
                line1 = br1.readLine();
            }

            JSONObject statsAll1 = new JSONObject(statsString);
            for(int i = statsAll1.getJSONArray("actualsTimeseries").length()-1; i > statsAll1.getJSONArray("actualsTimeseries").length()-6; i--)
            {
                if(statsAll1.getJSONArray("actualsTimeseries").getJSONObject(i).getString("newCases").equals("null"))
                {
                    MainActivity.usNewCases.add(0);
                }
                else
                {
                    MainActivity.usNewCases.add(Integer.parseInt(statsAll1.getJSONArray("actualsTimeseries").getJSONObject(i).getString("newCases")));
                }
            }

            for(int i = statsAll1.getJSONArray("actualsTimeseries").length()-1; i > statsAll1.getJSONArray("actualsTimeseries").length()-6; i--)
            {
                if(statsAll1.getJSONArray("actualsTimeseries").getJSONObject(i).getString("newDeaths").equals("null"))
                {
                    MainActivity.usNewDeaths.add(0);
                }
                else
                {
                    MainActivity.usNewDeaths.add(Integer.parseInt(statsAll1.getJSONArray("actualsTimeseries").getJSONObject(i).getString("newDeaths")));
                }
            }

            MainActivity.statsComplete = true;

            Log.d("stats",MainActivity.usNewCases.toString());
            Log.d("stats",MainActivity.usNewDeaths.toString());
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
