package com.example.maskup;

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
import android.widget.ArrayAdapter;


import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    static String zipCode = "08852";
    static String town;
    static String county;
    static String state;

    static double averageTemp;

    static GetLocation getLocation;

    static GetWeather getWeather;
    static ArrayList<Weather> forecasts;
    static boolean weatherComplete = false;

    static GetStats getStats;
    static boolean statsComplete = false;
    static int countyRiskLevel;
    static ArrayList<Integer> countyNewCases = new ArrayList<>();
    static ArrayList<Integer> countyNewDeaths = new ArrayList<>();
    static ArrayList<Integer> usNewCases = new ArrayList<>();
    static ArrayList<Integer> usNewDeaths = new ArrayList<>();

    static ArrayList<Place> places = new ArrayList<>();

    static Context context;

    static NavigationView navigationView;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.id_nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mask Up");

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.id_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);

        context = this;

        getWeather = new GetWeather();
        getWeather.execute();

        forecasts = new ArrayList<Weather>();

        getLocation = new GetLocation();
        getLocation.execute();

        getStats = new GetStats();
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
                if(statsComplete)
                {
                    Bundle statsBundle = new Bundle();
                    statsBundle.putInt("countyRiskLevel", countyRiskLevel);
                    statsBundle.putIntegerArrayList("countyNewCases", countyNewCases);
                    statsBundle.putIntegerArrayList("countyNewDeaths", countyNewDeaths);
                    statsBundle.putIntegerArrayList("usNewCases", usNewCases);
                    statsBundle.putIntegerArrayList("usNewDeaths", usNewDeaths);
                    StatsFragment statsFragment = new StatsFragment();
                    statsFragment.setArguments(statsBundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,statsFragment).commit();
                }
                else
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatsFragment()).commit();
                }
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