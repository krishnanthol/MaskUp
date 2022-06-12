package com.example.maskup;

import static java.util.Locale.US;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
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
        getSupportActionBar().setTitle("Mask Up");

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);

        //listView = findViewById(R.id.id_listView);

        context = this;

        geocoder = new Geocoder(this, Locale.US);
        //GetFrequentPlaces getFrequentPlaces = new GetFrequentPlaces();
        //getFrequentPlaces.execute();

        //getWeather = new GetWeather();
        //getWeather.execute();

        //getLocation = new GetLocation();
        //getLocation.execute();

        //getStats = new GetStats();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatsFragment()).commit();
                break;
            case R.id.nav_weather:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WeatherFragment()).commit();
                break;
            case R.id.nav_places:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PlacesFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}