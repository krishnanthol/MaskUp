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
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    static String zipCode;
    static String town;
    static String county;
    static String state;

    static double averageTemp;

    static GetLocation getLocation;

    static GetWeather getWeather;
    static ArrayList<Weather> forecasts;

    static GetStats getStats;
    static boolean statsComplete = false;
    static int countyRiskLevel;
    static ArrayList<Integer> countyNewCases = new ArrayList<>();
    static ArrayList<Integer> countyNewDeaths = new ArrayList<>();
    static ArrayList<Integer> usNewCases = new ArrayList<>();
    static ArrayList<Integer> usNewDeaths = new ArrayList<>();

    static boolean vaccinated;
    static boolean immunocompromised;

    static ArrayList<Place> places;

    static Context context;

    static NavigationView navigationView;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);
        zipCode = Paper.book().read("zipCode", "");
        town = Paper.book().read("town", "");
        county = Paper.book().read("county", "");
        state = Paper.book().read("state", "");
        places = Paper.book().read("places", new ArrayList<>());
        vaccinated = Paper.book().read("vaccinated",false);
        immunocompromised = Paper.book().read("immuno",false);

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

        context = this;

        forecasts = new ArrayList<Weather>();

        getStats = new GetStats();

        if(zipCode.equals(""))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }
        else
        {
            getWeather = new GetWeather();
            getWeather.execute();
            getStats.execute();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                public void run()
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                    navigationView.setCheckedItem(R.id.nav_home);
                }
            }, 5000);
        }
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
                if(!county.equals(""))
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WeatherFragment()).commit();
                }
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