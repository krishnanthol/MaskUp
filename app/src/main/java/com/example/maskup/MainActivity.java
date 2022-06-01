package com.example.maskup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    static String zipCode = "08852";
    static String county;
    static String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Geoapify API Key - a441e89ce9cf4d15b174eda7eec5beaf

        GetCounty getCounty = new GetCounty();
        getCounty.execute();

        //https://knowi.com/api/data/ipE4xJhLBkn8H8jisFisAdHKvepFR5I4bGzRySZ2aaXlJgie?entityName=Latest%20Day%20County%20Level%20Data&exportFormat=csv&c9SqlFilter=select%20*%20where%20State%20like%20New%20Jersey

        if(county != null && state!= null)
        {
            for(int i = 0; i < county.length(); i++)
            {
                if(county.charAt(i) == ' ')
                {
                    county = county.substring(0,i) + "%20" + county.substring(i+1,county.length());
                    i = 0;
                }
            }

            for(int i = 0; i < state.length(); i++)
            {
                if(state.charAt(i) == ' ')
                {
                    state = state.substring(0,i) + "%20" + state.substring(i+1,state.length());
                    i = 0;
                }
            }

            GetStats getStats = new GetStats();
            getStats.execute();
        }
    }
}