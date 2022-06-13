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

    static double averageTemp;

    static GetLocation getLocation;
    static GetWeather getWeather;

    static GetStats getStats;
    static boolean statsComplete = false;
    static int stateRiskLevel;
    static ArrayList<Integer> stateNewCases = new ArrayList<>();
    static ArrayList<Integer> stateNewDeaths = new ArrayList<>();
    static ArrayList<Integer> usNewCases = new ArrayList<>();
    static ArrayList<Integer> usNewDeaths = new ArrayList<>();

    static List<Address> addresses = new ArrayList<>();
    static Geocoder geocoder;
    static List<String> places = new ArrayList<>();

    static ListView listView;
    static Context context;
    static CustomAdapter customAdapter;

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

        //listView = findViewById(R.id.id_listView);

        context = this;

        geocoder = new Geocoder(this, Locale.US);
        //GetFrequentPlaces getFrequentPlaces = new GetFrequentPlaces();
        //getFrequentPlaces.execute();

        getWeather = new GetWeather();
        getWeather.execute();

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
                    Bundle bundle = new Bundle();
                    bundle.putInt("stateRiskLevel", stateRiskLevel);
                    bundle.putIntegerArrayList("stateNewCases", stateNewCases);
                    bundle.putIntegerArrayList("stateNewDeaths", stateNewDeaths);
                    bundle.putIntegerArrayList("stateNewCases", usNewCases);
                    bundle.putIntegerArrayList("stateNewDeaths", usNewDeaths);
                    StatsFragment statsFragment = new StatsFragment();
                    statsFragment.setArguments(bundle);
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