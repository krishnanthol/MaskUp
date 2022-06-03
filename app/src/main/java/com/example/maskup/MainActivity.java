package com.example.maskup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    static String zipCode = "08852";
    static String county;
    static String state;
    static String covidCases;

    static GetCounty getCounty;
    static GetStats getStats;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCounty = new GetCounty();
        getCounty.execute();

        getStats = new GetStats();
    }
}