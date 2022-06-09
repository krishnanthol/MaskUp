package com.example.maskup;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import org.json.JSONException;

import java.io.IOException;

public class GetFrequentPlaces extends AsyncTask<Void, Void, Void>
{
    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {
            MainActivity.addresses = MainActivity.geocoder.getFromLocationName("57 Potomac Road",5);
            Log.d("addresses",MainActivity.addresses.toString());

            MainActivity.customAdapter = new CustomAdapter(MainActivity.context,R.layout.adapter_custom,MainActivity.addresses);
            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    MainActivity.listView.setAdapter(MainActivity.customAdapter);

                    MainActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {

                        }
                    });
                }
            });
        }

        catch (IOException e)
        {

        }
        /*
        catch(JSONException e)
        {

        }

         */
        return null;
    }
}

