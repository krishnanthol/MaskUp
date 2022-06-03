package com.example.maskup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    static String zipCode = "08852";
    static String county;
    static String state;
    static String covidCases;
    static String longitude;
    static String latitude;

    static double averageTemp;

    static GetLocation getLocation;
    static GetStats getStats;
    static GetWeather getWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeather = new GetWeather();
        getWeather.execute();

        getLocation = new GetLocation();
        getLocation.execute();

        getStats = new GetStats();

        //https://apidocs.geoapify.com/playground/place-details
    }
}