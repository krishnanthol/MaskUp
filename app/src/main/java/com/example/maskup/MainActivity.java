package com.example.maskup;

import static java.util.Locale.US;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    static String zipCode = "08873";
    static String county;
    static String state;
    static String covidCases;

    static double averageTemp;

    static GetLocation getLocation;
    static GetStats getStats;
    static GetWeather getWeather;

    static List<Address> addresses = new ArrayList<>();
    static Geocoder geocoder;
    static List<String> places = new ArrayList<>();

    static ListView listView;
    static Context context;
    static CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listView);

        context = this;

        geocoder = new Geocoder(this, US);
        GetFrequentPlaces getFrequentPlaces = new GetFrequentPlaces();
        getFrequentPlaces.execute();

        //getWeather = new GetWeather();
        //getWeather.execute();

        //getLocation = new GetLocation();
        //getLocation.execute();

        //getStats = new GetStats();

    }
}