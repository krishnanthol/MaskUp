package com.example.maskup;

import static java.util.Locale.US;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

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

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //listView = findViewById(R.id.id_listView);

        context = this;

        geocoder = new Geocoder(this, US);
        GetFrequentPlaces getFrequentPlaces = new GetFrequentPlaces();
        //getFrequentPlaces.execute();

        //getWeather = new GetWeather();
        //getWeather.execute();

        //getLocation = new GetLocation();
        //getLocation.execute();

        //getStats = new GetStats();

    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    private void setSupportActionBar(CollapsingToolbarLayout toolbar)
    {

    }
}